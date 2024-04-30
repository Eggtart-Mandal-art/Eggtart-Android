package com.teamegg.eggtart.features.home.mandalart

import androidx.lifecycle.ViewModel
import com.teamegg.eggtart.common.feature.types.StringResource
import com.teamegg.eggtart.common.util.Logger
import com.teamegg.eggtart.common.util.Result
import com.teamegg.eggtart.domain.mandalart.model.ResCellModel
import com.teamegg.eggtart.domain.mandalart.model.ResCellTodosModel
import com.teamegg.eggtart.domain.mandalart.usecases.cell.GetMandalartCellUseCase
import com.teamegg.eggtart.domain.user.usecase.GetLocalUserTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
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
    private val getLocalUserTokenUseCase: GetLocalUserTokenUseCase,
) : ContainerHost<MandalartScreenState, MandalartSideEffect>, ViewModel() {

    override val container = container<MandalartScreenState, MandalartSideEffect>(MandalartScreenState())

    fun updateCellModel(cellModel: ResCellTodosModel) = intent {
        val prevIndex = state.mandalartCellList.indexOfFirst { it.id == cellModel.id }
        val newCellModel = ResCellModel(cellModel.step, cellModel.id, cellModel.color, cellModel.goal, cellModel.isCompleted)

        if (state.mandalartCellList[prevIndex] != newCellModel) {
            if (cellModel.goal == null) {
                postSideEffect(MandalartSideEffect.SnackBarRes(StringResource.toast_goal_deleted))
            } else {
                postSideEffect(MandalartSideEffect.SnackBarRes(StringResource.toast_goal_saved))
            }

            reduce {
                state.copy(
                    mandalartCellList = state.mandalartCellList.toMutableList().apply {
                        set(prevIndex, ResCellModel(cellModel.step, cellModel.id, cellModel.color, cellModel.goal, cellModel.isCompleted))
                    }
                )
            }
        }
    }

    fun getMandalartCells(sheetIds: List<Long>, depth: Int = 1, parentOrder: Int = 0) = intent {
        Logger.d("getMandalartCells Call")
        reduce {
            state.copy(mandalartLoading = true, sheetIds = sheetIds)
        }

        val accessToken = getLocalUserTokenUseCase().firstOrNull()?.accessToken

        Logger.d("accessToken: $accessToken")

        val mandalartCellsResult = getMandalartCellUseCase(
            accessToken = accessToken ?: "",
            sheetId = sheetIds.first(),
            depth = depth,
            parentOrder = parentOrder
        )

        Logger.d("manadalartCellsResult: $mandalartCellsResult")

        when (mandalartCellsResult) {
            is Result.Success -> {
                reduce {
                    state.copy(mandalartLoading = false, mandalartCellList = mandalartCellsResult.data)
                }
            }

            is Result.Failure -> {
                reduce {
                    state.copy(mandalartLoading = false, mandalartCellList = emptyList())
                }

                //TODO: 에러 로직 처리 필요
            }

            is Result.Exception -> {
                // TODO: 에러 로직 필요
            }
        }
    }
}