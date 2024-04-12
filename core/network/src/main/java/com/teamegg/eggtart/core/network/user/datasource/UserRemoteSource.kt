package com.teamegg.eggtart.core.network.user.datasource

/**
 *  Created by wonjin on 2024/04/12
 **/
interface
UserRemoteSource {
    suspend fun loginWithKakao(accessToken: String): String
}