package com.teamegg.eggtart.di.datasource

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
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.serialization.json.Json
import javax.inject.Singleton

/**
 *  Created by wonjin on 2024/04/09
 **/

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceProvideModule {
    @Singleton
    @Provides
    fun provideKtor(localUserRepository: LocalUserRepository): HttpClient = HttpClient(Android) {
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
//                loadTokens {
//                    val token = localUserRepository.userToken.lastOrNull()
//
//                    BearerTokens(
//                        accessToken = token?.accessToken ?: "",
//                        refreshToken = token?.refreshToken ?: ""
//                    )
//                }
                refreshTokens {
                    val token = client.get {
                        markAsRefreshTokenRequest()
                        url("/token")
                        bearerAuth(localUserRepository.userToken.lastOrNull()?.refreshToken ?: "")
                    }.body<UserTokenModel>()

                    localUserRepository.setUserToken(token)

                    BearerTokens(
                        accessToken = token.accessToken,
                        refreshToken = token.refreshToken,
                    )
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