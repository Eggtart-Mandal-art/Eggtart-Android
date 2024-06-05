package com.baker.eggtart.domain.user.repository

import com.baker.eggtart.domain.user.model.UserInfoModel
import com.baker.eggtart.domain.user.model.UserTokenModel
import kotlinx.coroutines.flow.Flow

/**
 *  Created by wonjin on 2024/04/12
 **/
interface LocalUserRepository {

    val userToken: Flow<UserTokenModel?>

    suspend fun setUserToken(userTokenModel: UserTokenModel?)

    val userInfo: Flow<UserInfoModel?>

    suspend fun setUserInfo(userInfoModel: UserInfoModel?)
}