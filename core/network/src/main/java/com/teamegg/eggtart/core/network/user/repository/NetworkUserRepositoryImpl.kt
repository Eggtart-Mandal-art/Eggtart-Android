package com.teamegg.eggtart.core.network.user.repository

import com.teamegg.eggtart.common.util.ServerResult
import com.teamegg.eggtart.core.network.user.datasource.UserRemoteSource
import com.teamegg.eggtart.core.network.user.entites.UserInfoEntity
import com.teamegg.eggtart.core.network.user.entites.UserTokenEntity
import com.teamegg.eggtart.core.network.user.mapper.toUserInfoModel
import com.teamegg.eggtart.core.network.user.mapper.toUserTokenModel
import com.teamegg.eggtart.domain.user.model.UserInfoModel
import com.teamegg.eggtart.domain.user.model.UserTokenModel
import com.teamegg.eggtart.domain.user.repository.NetworkUserRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/12
 **/

class NetworkUserRepositoryImpl @Inject constructor(private val userDataSource: UserRemoteSource) : NetworkUserRepository {
    override suspend fun loginWithKakao(kakaoAccessToken: String): ServerResult<UserTokenModel> {
        val response: UserTokenEntity

        return try {
            response = userDataSource.loginWithKakao(kakaoAccessToken)

            ServerResult.Success(response.toUserTokenModel())
        } catch (e: Exception) {
            ServerResult.parseException(e)
        }
    }

    override suspend fun getUserInfo(): ServerResult<UserInfoModel> {
        val response: UserInfoEntity

        return try {
            response = userDataSource.getUserInfo()

            ServerResult.Success(response.toUserInfoModel())
        } catch (e: Exception) {
            ServerResult.parseException(e)
        }
    }
}