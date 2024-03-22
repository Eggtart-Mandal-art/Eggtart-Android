package com.eggtart.eggtart.features.main

import com.eggtart.eggtart.common.BaseSideEffect
import com.eggtart.eggtart.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

/**
 *  Created by wonjin on 2024/03/21
 **/

@HiltViewModel
class MainViewModel : BaseViewModel<MainState, BaseSideEffect>() {
    override val container = container<MainState, BaseSideEffect>(MainState())

    fun testSideEffectPopup() = intent {
        postSideEffect(BaseSideEffect.ShowPopup(title = "title", content = "content", positiveButton = "확인", negativeButton = "취소", positiveClicked = {}, negativeClicked = {}))
    }
}