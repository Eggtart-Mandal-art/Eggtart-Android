package com.eggtart.eggtart.common

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

/**
 *  Created by wonjin on 2024/03/21
 **/
abstract class BaseViewModel<STATE : BaseState, SIDE_EFFECT : BaseSideEffect> : ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {
    fun showSideEffectPopup(sideEffectPopup: BaseSideEffect.ShowPopup) = intent {
        reduce {
            state.copy(sideEffectPopup = sideEffectPopup) as STATE
        }
    }

    fun dismissSideEffectPopup() = intent {
        reduce {
            state.copy(null) as STATE
        }
    }
}