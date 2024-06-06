package com.baker.eggtart.core.network.user.datasource

import com.baker.eggtart.core.network.user.entites.UserInfoEntity
import com.baker.eggtart.core.network.user.entites.UserTokenEntity

/**
 *  Created by wonjin on 2024/04/12
 **/
interface
UserRemoteSource {
    suspend fun loginWithKakao(kakaoAccessToken: String): UserTokenEntity

    suspend fun getUserInfo(accessToken: String): UserInfoEntity
}