package com.teamegg.eggtart.core.network.user.datasource

import com.teamegg.eggtart.core.network.user.entites.UserInfoEntity
import com.teamegg.eggtart.core.network.user.entites.UserTokenEntity

/**
 *  Created by wonjin on 2024/04/12
 **/
interface
UserRemoteSource {
    suspend fun loginWithKakao(kakaoAccessToken: String): UserTokenEntity

    suspend fun getUserInfo(): UserInfoEntity
}