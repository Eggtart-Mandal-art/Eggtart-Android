package com.teamegg.eggtart.features.home.mandalart

import androidx.lifecycle.ViewModel
import com.teamegg.eggtart.common.util.Logger
import com.teamegg.eggtart.common.util.Result
import com.teamegg.eggtart.domain.mandalart.usecases.cell.GetMandalartCellUseCase
import com.teamegg.eggtart.domain.user.usecase.GetLocalUserTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.lastOrNull
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
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
        }
    }
}