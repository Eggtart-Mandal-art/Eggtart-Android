package com.teamegg.eggtart.core.network.user.datasource

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/12
 **/
class UserRemoteSourceImpl @Inject constructor(private val ktorClient: HttpClient) : UserRemoteSource {
    private val loginKakao = "/login/kakao"
    private val refreshToken = "/token"
    private val userInfo = "/user/me"

    override suspend fun loginWithKakao(accessToken: String): String {
        val response = ktorClient.get(loginKakao) {
            parameter("accessToken", accessToken)
        }

        return response.bodyAsText()
    }
}