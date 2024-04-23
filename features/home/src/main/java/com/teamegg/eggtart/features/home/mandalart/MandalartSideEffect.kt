package com.teamegg.eggtart.features.home.mandalart

import androidx.annotation.StringRes

/**
 *  Created by wonjin on 2024/03/29
 **/

sealed class MandalartSideEffect {
    data class SnackBarRes(@StringRes val messageRes: Int): MandalartSideEffect()

    data class SnackBarString(val message: String): MandalartSideEffect()
}