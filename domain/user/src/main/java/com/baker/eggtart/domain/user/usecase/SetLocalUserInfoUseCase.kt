package com.baker.eggtart.domain.user.usecase

import com.baker.eggtart.domain.user.model.UserInfoModel
import com.baker.eggtart.domain.user.repository.LocalUserRepository
import javax.inject.Inject

/**
 * Created by 노원진 on 2024.04.12
 */
class SetLocalUserInfoUseCase @Inject constructor(private val userRepository: LocalUserRepository) {
    suspend operator fun invoke(userInfo: UserInfoModel?) = userRepository.setUserInfo(userInfo)
}