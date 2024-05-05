package com.teamegg.eggtart.features.login.state

import com.teamegg.eggtart.common.feature.components.ServerErrorDialogData

/**
 *  Created by wonjin on 2024/04/12
 **/
data class LoginState(
    val loginLoading: Boolean = false,
    val serverErrorDialogData: ServerErrorDialogData? = null
)