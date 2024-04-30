package com.teamegg.eggtart.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teamegg.eggtart.common.util.Logger
import com.teamegg.eggtart.common.util.ServerResult
import com.teamegg.eggtart.domain.kakao.usecase.KakaoLoginUseCase
import com.teamegg.eggtart.domain.mandalart.model.CellModel
import com.teamegg.eggtart.domain.mandalart.model.CellTodosModel
import com.teamegg.eggtart.domain.mandalart.usecases.sheet.CreateMandalartSheetUseCase
import com.teamegg.eggtart.domain.mandalart.usecases.sheet.GetMandalartSheetsUseCase
import com.teamegg.eggtart.domain.user.usecase.GetLocalUserTokenUseCase
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

    fun intentKakaoLogin() = intent {
        postSideEffect(MainSideEffect.NavigateLoginWithKakaoResult(kakaoLoginUseCase()))
    }

    fun navigateWriteGoal(cellModel: CellModel) = intent {
        postSideEffect(MainSideEffect.NavigateWriteGoal(cellModel))
    }

    fun navigateHome(cellModel: CellTodosModel?) = intent {
        postSideEffect(MainSideEffect.NavigateHome(sheetsIds = state.sheetIds, cellModel = cellModel))
    }

    fun intentGetLocalUserToken() = intent {
        getLocalUserTokenUseCase().collect {
            Logger.d(it.toString())

            if (it == null) {
                postSideEffect(MainSideEffect.NavigateLogin)
            } else {
                val sheets = getMandalartSheetsUseCase()
                when (sheets) {
                    is ServerResult.Success -> {
                        if (sheets.data.isEmpty()) {
                            val sheetId = createMandalartSheetUseCase()

                            when (sheetId) {
                                is ServerResult.Success -> {
                                    reduce {
                                        state.copy(sheetIds = listOf(sheetId.data))
                                    }
                                    postSideEffect(MainSideEffect.NavigateHome(listOf(sheetId.data)))
                                }

                                is ServerResult.Failure -> {
                                    // TODO: 에러 로직 처리 필요
                                }

                                is ServerResult.Exception -> {
                                    // TODO: 에러 로직 필요
                                }
                            }
                        } else {
                            reduce {
                                state.copy(sheetIds = sheets.data)
                            }
                            postSideEffect(MainSideEffect.NavigateHome(sheets.data))
                        }
                    }

                    is ServerResult.Failure -> {
                        // TODO: 에러 로직 필요
                    }

                    is ServerResult.Exception -> {
                        // TODO: 에러 로직 필요
                    }
                }
            }

            reduce {
                state.copy(true)
            }
        }
    }
}