package com.baker.eggtart.features.login.sideeffect

import com.baker.eggtart.common.util.ServerResult

/**
 *  Created by wonjin on 2024/04/12
 **/

sealed class LoginSideEffect {
    data class ServerErrorPopup(val serverResult: ServerResult<*>) : LoginSideEffect()
}