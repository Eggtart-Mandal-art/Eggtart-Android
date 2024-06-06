package com.baker.eggtart.core.network.user.datasource

import com.baker.eggtart.common.util.KtorClient
import com.baker.eggtart.common.util.KtorTokenClient
import com.baker.eggtart.core.network.BuildConfig
import com.baker.eggtart.core.network.user.entites.UserInfoEntity
import com.baker.eggtart.core.network.user.entites.UserTokenEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/12
 **/
class UserRemoteSourceImpl @Inject constructor(
    @KtorClient private val ktorClient: HttpClient,
    @KtorTokenClient private val ktorTokenClient: HttpClient
) : UserRemoteSource {
    private val loginKakao = "/login/kakao"
    private val userInfo = "/user/me"

    override suspend fun loginWithKakao(kakaoAccessToken: String): UserTokenEntity {
        val response = ktorClient.get(loginKakao) {
            parameter("accessToken", kakaoAccessToken)
            parameter("short", BuildConfig.SHORT_EXPIRED)
        }

        return response.body()
    }

    override suspend fun getUserInfo(accessToken: String): UserInfoEntity {
        val response = if (accessToken.isEmpty()) {
            ktorTokenClient.get(userInfo) {
                parameter("short", BuildConfig.SHORT_EXPIRED)
            }
        } else {
            ktorClient.get(userInfo) {
                bearerAuth(accessToken)

                parameter("short", BuildConfig.SHORT_EXPIRED)
            }
        }


        return response.body()
    }
}