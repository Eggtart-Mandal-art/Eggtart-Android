package com.teamegg.eggtart.datastore.user.repository

import com.teamegg.eggtart.datastore.user.datasource.UserLocalSource
import com.teamegg.eggtart.datastore.user.mapper.toUserInfoEntity
import com.teamegg.eggtart.datastore.user.mapper.toUserInfoModel
import com.teamegg.eggtart.datastore.user.mapper.toUserTokenEntity
import com.teamegg.eggtart.datastore.user.mapper.toUserTokenModel
import com.teamegg.eggtart.domain.user.model.UserInfoModel
import com.teamegg.eggtart.domain.user.model.UserTokenModel
import com.teamegg.eggtart.domain.user.repository.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/12
 **/
class LocalUserRepositoryImpl @Inject constructor(private val userLocalSource: UserLocalSource) : LocalUserRepository {
    override val userToken: Flow<UserTokenModel?>
        get() = userLocalSource.userToken.map {
            it?.toUserTokenModel()
        }

    override suspend fun setUserToken(userTokenModel: UserTokenModel?) {
        if (userTokenModel == null) {
            userLocalSource.setUserToken(null)
        } else {
            userLocalSource.setUserToken(userTokenModel.toUserTokenEntity())
        }
    }

    override val userInfo: Flow<UserInfoModel?>
        get() = userLocalSource.userInfo.map {
            it?.toUserInfoModel()
        }

    override suspend fun setUserInfo(userInfoModel: UserInfoModel?) {
        if (userInfoModel == null) {
            userLocalSource.setUserInfo(null)
        } else {
            userLocalSource.setUserInfo(userInfoModel.toUserInfoEntity())
        }
    }
}