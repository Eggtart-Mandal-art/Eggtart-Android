package com.eggtart.eggtart.common

import kotlinx.coroutines.flow.MutableStateFlow

/**
 *  Created by wonjin on 2024/03/21
 **/
sealed class BaseSideEffect {
    data class ShowToast(val text: String) : BaseSideEffect()

    data class ShowPopup(val code: String, val message: String) : BaseSideEffect()
}