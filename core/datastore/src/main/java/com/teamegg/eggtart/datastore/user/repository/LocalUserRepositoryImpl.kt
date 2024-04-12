package com.teamegg.eggtart.datastore.user.repository

import com.teamegg.eggtart.datastore.user.datasource.UserLocalSource
import com.teamegg.eggtart.domain.user.model.UserTokenModel
import com.teamegg.eggtart.domain.user.repository.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/12
 **/
class LocalUserRepositoryImpl @Inject constructor(private val userLocalSource: UserLocalSource) : LocalUserRepository {
    override val userToken: Flow<UserTokenModel?>
        get() = userLocalSource.userToken.map {
            if (it != null) {
                Json.decodeFromString<UserTokenModel>(it)
            } else {
                null
            }
        }

    override suspend fun setUserToken(userTokenModel: UserTokenModel?) {
        if (userTokenModel == null) {
            userLocalSource.setUserToken(null)
        } else {
            userLocalSource.setUserToken(Json.encodeToString(userTokenModel))
        }
    }
}