package com.baker.eggtart.features.home.mandalart

import androidx.lifecycle.ViewModel
import com.baker.eggtart.common.feature.components.ServerErrorDialogData
import com.baker.eggtart.common.feature.types.StringResource
import com.baker.eggtart.common.util.Logger
import com.baker.eggtart.common.util.ServerResult
import com.baker.eggtart.domain.mandalart.model.CellModel
import com.baker.eggtart.domain.mandalart.model.CellTodosModel
import com.baker.eggtart.domain.mandalart.usecases.cell.GetMandalartCellUseCase
import com.baker.eggtart.domain.user.usecase.SetLocalUserTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

@HiltViewModel
class MandalartViewModel @Inject constructor(
    private val getMandalartCellUseCase: GetMandalartCellUseCase,
    private val setLocalUserTokenUseCase: SetLocalUserTokenUseCase,
) : ContainerHost<MandalartScreenState, MandalartSideEffect>, ViewModel() {

    override val container = container<MandalartScreenState, MandalartSideEffect>(MandalartScreenState())

    fun intentClearLoginData() = intent {
        setLocalUserTokenUseCase(null)
    }

    fun intentSetServerErrorData(serverErrorDialogData: ServerErrorDialogData?) = intent {
        reduce {
            state.copy(serverErrorDialogData = serverErrorDialogData)
        }
    }

    fun intentUpdateCellModel(cellModel: CellTodosModel) = intent {
        val prevIndex = state.mandalartCellList.indexOfFirst { it.id == cellModel.id }
        val newCellModel = CellModel(cellModel.step, cellModel.id, cellModel.color, cellModel.goal, cellModel.isCompleted)

        if (state.mandalartCellList[prevIndex] != newCellModel) {
            if (cellModel.goal == null) {
                postSideEffect(MandalartSideEffect.SnackBarRes(StringResource.toast_goal_deleted))
            } else {
                postSideEffect(MandalartSideEffect.SnackBarRes(StringResource.toast_goal_saved))
            }

            reduce {
                state.copy(
                    mandalartCellList = state.mandalartCellList.toMutableList().apply {
                        set(prevIndex, CellModel(cellModel.step, cellModel.id, cellModel.color, cellModel.goal, cellModel.isCompleted))
                    }
                )
            }
        }
    }

    fun intentGetMandalartCells(sheetIds: List<Long>, depth: Int = 1, parentOrder: Int = 0) = intent {
        Logger.d("getMandalartCells Call")
        reduce {
            state.copy(mandalartLoading = true, sheetIds = sheetIds)
        }

        val mandalartCellsResult = getMandalartCellUseCase(
            sheetId = sheetIds.first(),
            depth = depth,
            parentOrder = parentOrder
        )

        Logger.d("manadalartCellsResult: $mandalartCellsResult")

        when (mandalartCellsResult) {
            is ServerResult.Success -> {
                reduce {
                    state.copy(mandalartLoading = false, mandalartCellList = mandalartCellsResult.data)
                }
            }

            else -> {
                postSideEffect(MandalartSideEffect.ServerErrorPopup(ServerCallType.GET_CELL_DATA, mandalartCellsResult))
                reduce {
                    state.copy(mandalartLoading = false)
                }
            }
        }
    }
}