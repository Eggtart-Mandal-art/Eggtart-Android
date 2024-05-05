package com.teamegg.eggtart.datastore.user.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.teamegg.eggtart.datastore.user.model.UserInfoEntity
import com.teamegg.eggtart.datastore.user.model.UserTokenEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/12
 **/
class UserLocalSourceImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : UserLocalSource {
    private val userTokenKey = stringPreferencesKey("user_token")
    private val userInfoKey = stringPreferencesKey("user_info")

    override val userToken: Flow<UserTokenEntity?>
        get() = dataStore.data.map {
            try {
                Json.decodeFromString(it[userTokenKey]!!)
            } catch (e: Exception) {
                null
            }
        }

    override suspend fun setUserToken(token: UserTokenEntity?) {
        dataStore.edit {
            if (token == null) {
                it.remove(userTokenKey)
            } else {
                it[userTokenKey] = Json.encodeToString(token)
            }
        }
    }

    override val userInfo: Flow<UserInfoEntity?>
        get() = dataStore.data.map {
            try {
                Json.decodeFromString<UserInfoEntity>(it[userInfoKey]!!)
            } catch (e: Exception) {
                null
            }
        }

    override suspend fun setUserInfo(info: UserInfoEntity?) {
        dataStore.edit {
            if (info == null) {
                it.remove(userInfoKey)
            } else {
                it[userInfoKey] = Json.encodeToString(info)
            }
        }
    }
}