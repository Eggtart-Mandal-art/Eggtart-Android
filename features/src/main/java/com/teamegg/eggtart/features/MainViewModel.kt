package com.teamegg.eggtart.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teamegg.eggtart.common.feature.components.ServerErrorDialogData
import com.teamegg.eggtart.common.util.Logger
import com.teamegg.eggtart.common.util.ServerResult
import com.teamegg.eggtart.domain.kakao.usecase.KakaoLoginUseCase
import com.teamegg.eggtart.domain.mandalart.model.CellModel
import com.teamegg.eggtart.domain.mandalart.model.CellTodosModel
import com.teamegg.eggtart.domain.mandalart.usecases.sheet.CreateMandalartSheetUseCase
import com.teamegg.eggtart.domain.mandalart.usecases.sheet.GetMandalartSheetsUseCase
import com.teamegg.eggtart.domain.user.usecase.GetLocalUserTokenUseCase
import com.teamegg.eggtart.domain.user.usecase.SetLocalUserTokenUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

/**
 *  Created by wonjin on 2024/03/21
 **/

class MainViewModel @AssistedInject constructor(
    @Assisted private val kakaoLoginUseCase: KakaoLoginUseCase,
    private val getLocalUserTokenUseCase: GetLocalUserTokenUseCase,
    private val setLocalUserTokenUseCase: SetLocalUserTokenUseCase,
    private val getMandalartSheetsUseCase: GetMandalartSheetsUseCase,
    private val createMandalartSheetUseCase: CreateMandalartSheetUseCase
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {

    override val container = container<MainState, MainSideEffect>(MainState())

    @AssistedFactory
    interface MainViewModelFactory {
        fun create(kakaoLoginUseCase: KakaoLoginUseCase): MainViewModel
    }

    companion object {
        fun create(factory: MainViewModelFactory, kakaoLoginUseCase: KakaoLoginUseCase) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return factory.create(kakaoLoginUseCase) as T
            }
        }
    }

    fun intentClearLoginData() = intent {
        setLocalUserTokenUseCase(null)
    }

    fun intentSetServerErrorData(serverErrorDialogData: ServerErrorDialogData?) = intent {
        reduce {
            state.copy(serverErrorDialogData = serverErrorDialogData)
        }
    }

    fun intentKakaoLogin() = intent {
        postSideEffect(MainSideEffect.NavigateLoginWithKakaoResult(kakaoLoginUseCase()))
    }

    fun intentNavigateWriteGoal(cellModel: CellModel) = intent {
        postSideEffect(MainSideEffect.NavigateWriteGoal(cellModel))
    }

    fun intentNavigateHome(cellModel: CellTodosModel?) = intent {
        postSideEffect(MainSideEffect.NavigateHome(sheetsIds = state.sheetIds, cellModel = cellModel))
    }

    fun intentGetLocalUserToken() = intent {
        getLocalUserTokenUseCase().collect {
            Logger.d(it.toString())

            if (it == null) {
                postSideEffect(MainSideEffect.NavigateLogin)
            } else {
                when (val getSheetsResult = getMandalartSheetsUseCase()) {
                    is ServerResult.Success -> {
                        if (getSheetsResult.data.isEmpty()) {
                            when (val createSheetResult = createMandalartSheetUseCase()) {
                                is ServerResult.Success -> {
                                    reduce {
                                        state.copy(sheetIds = listOf(createSheetResult.data))
                                    }
                                    postSideEffect(MainSideEffect.NavigateHome(listOf(createSheetResult.data)))
                                }

                                else -> {
                                    postSideEffect(MainSideEffect.ServerErrorPopup(ServerCallType.CREATE_MANDALART_SHEET, createSheetResult))
                                }
                            }
                        } else {
                            reduce {
                                state.copy(sheetIds = getSheetsResult.data)
                            }
                            postSideEffect(MainSideEffect.NavigateHome(getSheetsResult.data))
                        }
                    }

                    else -> {
                        postSideEffect(MainSideEffect.ServerErrorPopup(ServerCallType.GET_MANDALART_SHEET, getSheetsResult))
                    }
                }
            }

            reduce {
                state.copy(initialized = true)
            }
        }
    }
}