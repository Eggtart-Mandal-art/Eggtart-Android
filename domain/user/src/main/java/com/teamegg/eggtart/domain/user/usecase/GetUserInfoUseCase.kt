package com.teamegg.eggtart.domain.user.usecase

import com.teamegg.eggtart.common.util.ServerResult
import com.teamegg.eggtart.domain.user.model.UserInfoModel
import com.teamegg.eggtart.domain.user.repository.NetworkUserRepository
import javax.inject.Inject

/**
 * Created by 노원진 on 2024.04.12
 */
class GetUserInfoUseCase @Inject constructor(private val networkUserRepository: NetworkUserRepository) {

    suspend operator fun invoke(accessToken: String = ""): ServerResult<UserInfoModel> = networkUserRepository.getUserInfo(accessToken)
}