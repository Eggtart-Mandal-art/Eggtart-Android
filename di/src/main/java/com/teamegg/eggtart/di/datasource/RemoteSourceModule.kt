package com.teamegg.eggtart.di.datasource

import com.teamegg.eggtart.common.util.KtorClient
import com.teamegg.eggtart.common.util.KtorTokenClient
import com.teamegg.eggtart.common.util.Logger
import com.teamegg.eggtart.core.network.mandalart.datasource.MandalartRemoteSource
import com.teamegg.eggtart.core.network.mandalart.datasource.MandalartRemoteSourceImpl
import com.teamegg.eggtart.core.network.user.datasource.UserRemoteSource
import com.teamegg.eggtart.core.network.user.datasource.UserRemoteSourceImpl
import com.teamegg.eggtart.di.BuildConfig
import com.teamegg.eggtart.domain.user.model.UserTokenModel
import com.teamegg.eggtart.domain.user.repository.LocalUserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.json.Json
import javax.inject.Singleton

/**
 *  Created by wonjin on 2024/04/09
 **/

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceProvideModule {
    @KtorClient
    @Singleton
    @Provides
    fun provideKtorClient(): HttpClient = HttpClient(Android) {
        install(Logging) {
            logger = object : io.ktor.client.plugins.logging.Logger {
                override fun log(message: String) {
                    Logger.d("EGGTART_SERVER: $message")
                }
            }
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                }
            )
        }

        defaultRequest {
            header(HttpHeaders.ContentType, ContentType.Application.Json)

            url {
                host = BuildConfig.EGGTART_API_URL
                protocol = URLProtocol.HTTPS
            }
        }

        addDefaultResponseValidation()
    }

    @KtorTokenClient
    @Singleton
    @Provides
    fun provideKtorTokenClient(localUserRepository: LocalUserRepository, @KtorClient ktorClient: HttpClient): HttpClient = HttpClient(Android) {
        install(Logging) {
            logger = object : io.ktor.client.plugins.logging.Logger {
                override fun log(message: String) {
                    Logger.d("EGGTART_SERVER: $message")
                }
            }
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                }
            )
        }

        install(Auth) {
            bearer {
                loadTokens {
                    val token = localUserRepository.userToken.firstOrNull()

                    Logger.d("accessToken: $token")

                    BearerTokens(
                        accessToken = token?.accessToken ?: "",
                        refreshToken = token?.refreshToken ?: ""
                    )
                }

                refreshTokens {
                    try {
                        val token = ktorClient.get {
                            markAsRefreshTokenRequest()
                            url("/token")
                            bearerAuth(localUserRepository.userToken.firstOrNull()?.refreshToken ?: "")
                        }.body<UserTokenModel>()

                        localUserRepository.setUserToken(token)

                        BearerTokens(
                            accessToken = token.accessToken,
                            refreshToken = token.refreshToken,
                        )
                    } catch (e: Exception) {
                        localUserRepository.setUserToken(null)

                        BearerTokens(
                            accessToken = "",
                            refreshToken = "",
                        )
                    }
                }
            }
        }

        defaultRequest {
            header(HttpHeaders.ContentType, ContentType.Application.Json)

            url {
                host = BuildConfig.EGGTART_API_URL
                protocol = URLProtocol.HTTPS
            }
        }

        addDefaultResponseValidation()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteSourceBindsModule {

    @Singleton
    @Binds
    abstract fun bindsUserDataSource(userDataSource: UserRemoteSourceImpl): UserRemoteSource

    @Singleton
    @Binds
    abstract fun bindsMandalartRemoteSource(mandalartRemoteSource: MandalartRemoteSourceImpl): MandalartRemoteSource
}