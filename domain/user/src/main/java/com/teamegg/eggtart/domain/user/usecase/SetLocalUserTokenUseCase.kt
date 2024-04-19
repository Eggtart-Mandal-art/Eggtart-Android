package com.teamegg.eggtart.domain.user.usecase

import com.teamegg.eggtart.domain.user.model.UserTokenModel
import com.teamegg.eggtart.domain.user.repository.LocalUserRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/12
 **/
class SetLocalUserTokenUseCase @Inject constructor(private val localUserRepository: LocalUserRepository) {
    suspend operator fun invoke(userToken: UserTokenModel?) {
        localUserRepository.setUserToken(userToken)
    }
}