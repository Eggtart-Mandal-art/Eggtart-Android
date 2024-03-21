package com.eggtart.eggtart.common

/**
 *  Created by wonjin on 2024/03/21
 **/
sealed class BaseSideEffect {
    data class ShowToast(val text: String) : BaseSideEffect()
    data class ShowSnackBar(val text: String) : BaseSideEffect()
    data class ShowPopup(val title: String?, val content: String, val positiveButton: String, val negativeButton: String?) : BaseSideEffect()
}