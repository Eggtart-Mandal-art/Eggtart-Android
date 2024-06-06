package com.baker.eggtart.features.home.mandalart

import androidx.annotation.StringRes
import com.baker.eggtart.common.util.ServerResult

/**
 *  Created by wonjin on 2024/03/29
 **/

sealed class MandalartSideEffect {
    data class SnackBarRes(@StringRes val messageRes: Int) : MandalartSideEffect()

    data class SnackBarString(val message: String) : MandalartSideEffect()

    data class ServerErrorPopup(val type: ServerCallType, val serverResult: ServerResult<*>) : MandalartSideEffect()
}

enum class ServerCallType {
    GET_CELL_DATA, GET_CELL_CHILDREN
}