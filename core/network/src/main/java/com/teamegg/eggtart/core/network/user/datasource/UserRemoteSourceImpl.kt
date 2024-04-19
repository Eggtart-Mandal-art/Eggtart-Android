package com.teamegg.eggtart.core.network.user.datasource

import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/12
 **/
class UserRemoteSourceImpl @Inject constructor(private val ktorClient: HttpClient) : UserRemoteSource {
    private val loginKakao = "/login/kakao"
    private val userInfo = "/user/me"

    override suspend fun loginWithKakao(kakaoAccessToken: String): String {
        val response = ktorClient.get(loginKakao) {
            parameter("accessToken", kakaoAccessToken)
        }

        return response.bodyAsText()
    }

    override suspend fun getUserInfo(accessToken: String): String {
        val response = ktorClient.get(userInfo) {
            bearerAuth(accessToken)
        }

        return response.bodyAsText()
    }
}