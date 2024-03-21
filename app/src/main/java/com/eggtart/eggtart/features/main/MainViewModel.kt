package com.eggtart.eggtart.features.main

import com.eggtart.eggtart.common.BaseSideEffect
import com.eggtart.eggtart.common.BaseViewModel
import org.orbitmvi.orbit.viewmodel.container

/**
 *  Created by wonjin on 2024/03/21
 **/
class MainViewModel : BaseViewModel<MainState, BaseSideEffect>() {
    override val container = container<MainState, BaseSideEffect>(MainState())

}