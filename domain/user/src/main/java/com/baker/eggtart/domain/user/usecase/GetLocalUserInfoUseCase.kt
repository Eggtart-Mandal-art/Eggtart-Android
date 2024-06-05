package com.baker.eggtart.domain.user.usecase

import com.baker.eggtart.domain.user.model.UserInfoModel
import com.baker.eggtart.domain.user.repository.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by 노원진 on 2024.04.12
 */
class GetLocalUserInfoUseCase @Inject constructor(private val localUserRepository: LocalUserRepository) {

    operator fun invoke(): Flow<UserInfoModel?> = localUserRepository.userInfo
}