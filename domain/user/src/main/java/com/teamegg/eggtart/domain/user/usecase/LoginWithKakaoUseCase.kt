package com.teamegg.eggtart.domain.user.usecase

import com.teamegg.eggtart.common.util.Result
import com.teamegg.eggtart.domain.user.model.UserTokenModel
import com.teamegg.eggtart.domain.user.repository.NetworkUserRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/12
 **/
class LoginWithKakaoUseCase @Inject constructor(private val userRepository: NetworkUserRepository) {
    suspend operator fun invoke(kakaoAccessToken: String): Result<UserTokenModel> = userRepository.loginWithKakao(kakaoAccessToken)
}