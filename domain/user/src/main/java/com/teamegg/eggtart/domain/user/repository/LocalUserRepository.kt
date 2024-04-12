package com.teamegg.eggtart.domain.user.repository

import com.teamegg.eggtart.domain.user.model.UserTokenModel
import kotlinx.coroutines.flow.Flow

/**
 *  Created by wonjin on 2024/04/12
 **/
interface LocalUserRepository {

    val userToken: Flow<UserTokenModel?>

    suspend fun setUserToken(userTokenModel: UserTokenModel?)
}