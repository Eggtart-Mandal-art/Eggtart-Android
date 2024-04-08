package com.teamegg.eggtart.features.home.mandalart

import androidx.lifecycle.ViewModel
import com.teamegg.eggtart.domain.mandalart.usecases.cell.GetMandalartCellUseCase
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
class MandalartViewModel @Inject constructor(private val getMandalartCellUseCase: GetMandalartCellUseCase) : ContainerHost<MandalartScreenState, MandalartSideEffect>, ViewModel() {
    override val container = container<MandalartScreenState, MandalartSideEffect>(
        MandalartScreenState(
            mandalartLoading = false,
            mandalartCellList = listOf(),
        )
    ) {
        getMandalartCells()
    }

    fun getMandalartCells() = intent {
        reduce { state.copy(mandalartLoading = true) }

        val mandalartCells = getMandalartCellUseCase.invoke(1)

        reduce { state.copy(mandalartLoading = false, mandalartCellList = mandalartCells) }
    }
}