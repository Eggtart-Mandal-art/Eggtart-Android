package com.teamegg.eggtart.features.login.sideeffect

/**
 *  Created by wonjin on 2024/04/12
 **/

sealed class LoginSideEffect {
    data object ShowErrorPopup : LoginSideEffect()
}