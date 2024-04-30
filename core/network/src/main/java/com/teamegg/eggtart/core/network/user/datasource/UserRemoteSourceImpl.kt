package com.teamegg.eggtart.core.network.user.datasource

import com.teamegg.eggtart.common.util.KtorClient
import com.teamegg.eggtart.common.util.KtorTokenClient
import com.teamegg.eggtart.core.network.user.entites.UserInfoEntity
import com.teamegg.eggtart.core.network.user.entites.UserTokenEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
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
        }

        return response.body()
    }

    override suspend fun getUserInfo(): UserInfoEntity {
        val response = ktorTokenClient.get(userInfo)

        return response.body()
    }
}