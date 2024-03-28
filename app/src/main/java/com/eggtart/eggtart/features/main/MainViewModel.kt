package com.eggtart.eggtart.features.main

import com.eggtart.eggtart.common.BaseSideEffect
import com.eggtart.eggtart.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/21
 **/

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<MainState, BaseSideEffect>() {
    override val container = container<MainState, BaseSideEffect>(MainState())
}