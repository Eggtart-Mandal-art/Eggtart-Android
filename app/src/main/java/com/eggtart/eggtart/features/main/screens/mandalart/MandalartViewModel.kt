package com.eggtart.eggtart.features.main.screens.mandalart

import androidx.lifecycle.viewModelScope
import com.eggtart.eggtart.common.BaseSideEffect
import com.eggtart.eggtart.common.BaseViewModel
import com.eggtart.eggtart.domain.usecase.remote.TestGetRemoteMandalartCellsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

@HiltViewModel
class MandalartViewModel @Inject constructor() : BaseViewModel<MandalartScreenState, BaseSideEffect>() {
    private val getMandalartCellsUseCase: TestGetRemoteMandalartCellsUseCase = TestGetRemoteMandalartCellsUseCase()

    override val container: Container<MandalartScreenState, BaseSideEffect> = container(
        MandalartScreenState(
            sideEffectPopup = null,
            mandalartLoading = false,
            mandalartCellList = listOf(),
        )
    )

    fun getMandalartCells() = intent {
        reduce { state.copy(mandalartLoading = true) }

        val mandalartCells = getMandalartCellsUseCase.invoke(1)

        reduce { state.copy(mandalartLoading = false, mandalartCellList = mandalartCells) }
    }
}