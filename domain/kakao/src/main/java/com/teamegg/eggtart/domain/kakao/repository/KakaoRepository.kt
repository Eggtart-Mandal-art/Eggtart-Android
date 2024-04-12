package com.teamegg.eggtart.domain.kakao.repository

/**
 *  Created by wonjin on 2024/04/11
 **/

interface KakaoRepository {
    suspend fun loginWithKakao(): Result<String?>
}