package com.eggtart.eggtart.features.main.screens.mandalart

import com.eggtart.eggtart.common.BaseSideEffect
import com.eggtart.eggtart.common.BaseState
import com.eggtart.eggtart.domain.model.remote.ResponseMandalartCellModel
import kotlinx.coroutines.flow.Flow

/**
 *  Created by wonjin on 2024/03/28
 **/

data class MandalartScreenState(
    override val sideEffectPopup: BaseSideEffect.ShowPopup?,
    val mandalartLoading: Boolean,
    val mandalartCellList: List<ResponseMandalartCellModel>,
) : BaseState(sideEffectPopup)