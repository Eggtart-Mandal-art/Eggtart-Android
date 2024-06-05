package com.baker.eggtart.datastore.user.datasource

import com.baker.eggtart.datastore.user.model.UserInfoEntity
import com.baker.eggtart.datastore.user.model.UserTokenEntity
import kotlinx.coroutines.flow.Flow

/**
 *  Created by wonjin on 2024/04/12
 **/
interface UserLocalSource {
    val userToken: Flow<UserTokenEntity?>

    suspend fun setUserToken(token: UserTokenEntity?)

    val userInfo: Flow<UserInfoEntity?>

    suspend fun setUserInfo(info: UserInfoEntity?)
}