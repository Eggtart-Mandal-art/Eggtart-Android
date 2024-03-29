package com.eggtart.eggtart.features.home.mandalart

import androidx.lifecycle.ViewModel
import com.eggtart.eggtart.domain.usecase.remote.TestGetRemoteMandalartCellsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

@HiltViewModel
class MandalartViewModel @Inject constructor() : ContainerHost<MandalartScreenState, MandalartSideEffect>, ViewModel() {
    private val getMandalartCellsUseCase: TestGetRemoteMandalartCellsUseCase = TestGetRemoteMandalartCellsUseCase()

    override val container = container<MandalartScreenState, MandalartSideEffect>(
        MandalartScreenState(
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