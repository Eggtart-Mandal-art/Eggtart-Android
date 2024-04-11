package com.teamegg.eggtart.domain.kakao.repository

import com.kakao.sdk.auth.model.OAuthToken

/**
 *  Created by wonjin on 2024/04/11
 **/

interface KakaoRepository {
    suspend fun loginWithKakao(): Result<OAuthToken?>
}