package com.teamegg.eggtart.datastore.user.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/12
 **/
class UserLocalSourceImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : UserLocalSource {
    private val userTokenKey = stringPreferencesKey("user_token")

    override val userToken: Flow<String?>
        get() = dataStore.data.map {
            it[userTokenKey]
        }

    override suspend fun setUserToken(token: String?) {
        dataStore.edit {
            if (token == null) {
                it.remove(userTokenKey)
            } else {
                it[userTokenKey] = token
            }
        }
    }
}