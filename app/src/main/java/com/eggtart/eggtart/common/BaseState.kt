package com.eggtart.eggtart.common

/**
 *  Created by wonjin on 2024/03/21
 **/
open class BaseState(open val sideEffectPopup: BaseSideEffect.ShowPopup?) {
    fun copy(sideEffectPopup: BaseSideEffect.ShowPopup? = this.sideEffectPopup): BaseState = BaseState(sideEffectPopup)
}