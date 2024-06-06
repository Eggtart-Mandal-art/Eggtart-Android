package com.baker.eggtart.domain.user.usecase

import com.baker.eggtart.common.util.ServerResult
import com.baker.eggtart.domain.user.model.UserTokenModel
import com.baker.eggtart.domain.user.repository.NetworkUserRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/12
 **/
class LoginWithKakaoUseCase @Inject constructor(private val userRepository: NetworkUserRepository) {
    suspend operator fun invoke(kakaoAccessToken: String): ServerResult<UserTokenModel> = userRepository.loginWithKakao(kakaoAccessToken)
}