package com.teamegg.eggtart.domain.user.usecase

import com.teamegg.eggtart.domain.user.model.UserTokenModel
import com.teamegg.eggtart.domain.user.repository.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/12
 **/
class GetLocalUserTokenUseCase @Inject constructor(private val localUserRepository: LocalUserRepository) {

    operator fun invoke(): Flow<UserTokenModel?> = localUserRepository.userToken
}