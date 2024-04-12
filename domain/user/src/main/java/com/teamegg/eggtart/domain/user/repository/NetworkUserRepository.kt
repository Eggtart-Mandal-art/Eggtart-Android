package com.teamegg.eggtart.domain.user.repository

import com.teamegg.eggtart.common.util.Result
import com.teamegg.eggtart.domain.user.model.UserTokenModel

/**
 *  Created by wonjin on 2024/04/12
 **/
interface NetworkUserRepository {
    suspend fun loginWithKakao(accessToken: String): Result<UserTokenModel>
}