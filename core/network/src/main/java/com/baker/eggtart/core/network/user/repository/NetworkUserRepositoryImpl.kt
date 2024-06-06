package com.baker.eggtart.core.network.user.repository

import com.baker.eggtart.common.util.ServerResult
import com.baker.eggtart.core.network.user.datasource.UserRemoteSource
import com.baker.eggtart.core.network.user.entites.UserInfoEntity
import com.baker.eggtart.core.network.user.entites.UserTokenEntity
import com.baker.eggtart.core.network.user.mapper.toUserInfoModel
import com.baker.eggtart.core.network.user.mapper.toUserTokenModel
import com.baker.eggtart.domain.user.model.UserInfoModel
import com.baker.eggtart.domain.user.model.UserTokenModel
import com.baker.eggtart.domain.user.repository.NetworkUserRepository
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

    override suspend fun getUserInfo(accessToken: String): ServerResult<UserInfoModel> {
        val response: UserInfoEntity

        return try {
            response = userDataSource.getUserInfo(accessToken)

            ServerResult.Success(response.toUserInfoModel())
        } catch (e: Exception) {
            ServerResult.parseException(e)
        }
    }
}