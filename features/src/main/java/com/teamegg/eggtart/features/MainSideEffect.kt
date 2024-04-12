package com.teamegg.eggtart.features

/**
 *  Created by wonjin on 2024/03/29
 **/
sealed class MainSideEffect {
    data class NavigateLoginWithKakaoResult(val kakaoAccessToken: Result<String?>) : MainSideEffect()

    data object NavigateLogin : MainSideEffect()

    data object NavigateHome : MainSideEffect()
}