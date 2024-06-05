package com.baker.eggtart.domain.user.usecase

import com.baker.eggtart.common.util.ServerResult
import com.baker.eggtart.domain.user.model.UserInfoModel
import com.baker.eggtart.domain.user.repository.NetworkUserRepository
import javax.inject.Inject

/**
 * Created by 노원진 on 2024.04.12
 */
class GetUserInfoUseCase @Inject constructor(private val networkUserRepository: NetworkUserRepository) {

    suspend operator fun invoke(accessToken: String = ""): ServerResult<UserInfoModel> = networkUserRepository.getUserInfo(accessToken)
}