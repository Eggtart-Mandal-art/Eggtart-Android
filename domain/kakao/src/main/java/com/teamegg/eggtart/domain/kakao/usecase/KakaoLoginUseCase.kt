package com.teamegg.eggtart.domain.kakao.usecase

import com.kakao.sdk.auth.model.OAuthToken
import com.teamegg.eggtart.domain.kakao.repository.KakaoRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/11
 **/
class KakaoLoginUseCase @Inject constructor(private val kakaoRepository: KakaoRepository) {

    suspend operator fun invoke(): Result<OAuthToken?> = kakaoRepository.loginWithKakao()
}