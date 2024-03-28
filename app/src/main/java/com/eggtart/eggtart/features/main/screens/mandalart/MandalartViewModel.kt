package com.eggtart.eggtart.features.main.screens.mandalart

import com.eggtart.eggtart.common.BaseSideEffect
import com.eggtart.eggtart.common.BaseViewModel
import com.eggtart.eggtart.domain.usecase.local.mandalart.sheet.GetMandalartSheetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

@HiltViewModel
class MandalartViewModel @Inject constructor(private val getMandalartSheetsUseCase: GetMandalartSheetsUseCase) : BaseViewModel<MandalartScreenState, BaseSideEffect>() {
    override val container: Container<MandalartScreenState, BaseSideEffect> = runBlocking {
        container(
            MandalartScreenState(
                sideEffectPopup = null,
                mandalartSheetList = getMandalartSheetsUseCase.invoke(),
            )
        )
    }
}