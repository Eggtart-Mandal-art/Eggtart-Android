package com.eggtart.eggtart.features.main

import com.eggtart.eggtart.common.BaseSideEffect
import com.eggtart.eggtart.common.BaseState

/**
 *  Created by wonjin on 2024/03/21
 **/
class MainState(override val sideEffectPopup: BaseSideEffect.ShowPopup? = null) : BaseState(sideEffectPopup = sideEffectPopup)