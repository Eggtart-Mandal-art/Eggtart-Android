package com.teamegg.eggtart.core.network.user.repository

import com.teamegg.eggtart.common.util.Result
import com.teamegg.eggtart.common.util.ServerErrorModel
import com.teamegg.eggtart.core.network.user.datasource.UserRemoteSource
import com.teamegg.eggtart.domain.user.model.UserInfoModel
import com.teamegg.eggtart.domain.user.model.UserTokenModel
import com.teamegg.eggtart.domain.user.repository.NetworkUserRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/12
 **/

class NetworkUserRepositoryImpl @Inject constructor(private val userDataSource: UserRemoteSource) : NetworkUserRepository {
    override suspend fun loginWithKakao(kakaoAccessToken: String): Result<UserTokenModel> {
        val response = userDataSource.loginWithKakao(kakaoAccessToken)

        return try {
            Result.Success(Json.decodeFromString<UserTokenModel>(response))
        } catch (e: Exception) {
            try {
                Result.Failure(Json.decodeFromString<ServerErrorModel>(response))
            } catch (e: Exception) {
                Result.Failure(null)
            }
        }
    }

    override suspend fun getUserInfo(accessToken: String): Result<UserInfoModel> {
        val response = userDataSource.getUserInfo(accessToken)

        return try {
            Result.Success(Json.decodeFromString<UserInfoModel>(response))
        } catch (e: Exception) {
            try {
                Result.Failure(Json.decodeFromString<ServerErrorModel>(response))
            } catch (e: Exception) {
                Result.Failure(null)
            }
        }
    }
}