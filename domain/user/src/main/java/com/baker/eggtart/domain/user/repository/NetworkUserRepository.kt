package com.baker.eggtart.domain.user.repository

import com.baker.eggtart.common.util.ServerResult
import com.baker.eggtart.domain.user.model.UserInfoModel
import com.baker.eggtart.domain.user.model.UserTokenModel

/**
 *  Created by wonjin on 2024/04/12
 **/
interface NetworkUserRepository {
    suspend fun loginWithKakao(kakaoAccessToken: String): ServerResult<UserTokenModel>

    suspend fun getUserInfo(accessToken: String): ServerResult<UserInfoModel>
}