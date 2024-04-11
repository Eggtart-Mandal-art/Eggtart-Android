package com.teamegg.eggtart.features

import com.kakao.sdk.auth.model.OAuthToken

/**
 *  Created by wonjin on 2024/03/29
 **/
sealed class MainSideEffect {
    data class NavigateLoginWithKakaoResult(val kakaoLoginData: Result<OAuthToken?>) : MainSideEffect()
}