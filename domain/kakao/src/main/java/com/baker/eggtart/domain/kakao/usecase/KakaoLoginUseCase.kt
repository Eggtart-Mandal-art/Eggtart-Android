package com.baker.eggtart.domain.kakao.usecase

import com.baker.eggtart.domain.kakao.repository.KakaoRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/11
 **/

class KakaoLoginUseCase @Inject constructor(private val kakaoRepository: KakaoRepository) {

    suspend operator fun invoke(): Result<String?> = kakaoRepository.loginWithKakao()
}