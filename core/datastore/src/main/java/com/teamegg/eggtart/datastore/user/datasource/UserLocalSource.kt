package com.teamegg.eggtart.datastore.user.datasource

import kotlinx.coroutines.flow.Flow

/**
 *  Created by wonjin on 2024/04/12
 **/
interface UserLocalSource {
    val userToken: Flow<String?>

    suspend fun setUserToken(token: String?)

    val userInfo: Flow<String?>

    suspend fun setUserInfo(info: String?)
}