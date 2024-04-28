package com.teamegg.eggtart.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teamegg.eggtart.common.util.Logger
import com.teamegg.eggtart.common.util.Result
import com.teamegg.eggtart.domain.kakao.usecase.KakaoLoginUseCase
import com.teamegg.eggtart.domain.mandalart.model.ResCellModel
import com.teamegg.eggtart.domain.mandalart.model.ResCellTodosModel
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

    fun navigateWriteGoal(cellModel: ResCellModel) = intent {
        postSideEffect(MainSideEffect.NavigateWriteGoal(cellModel))
    }

    fun navigateHome(cellModel: ResCellTodosModel?) = intent {
        postSideEffect(MainSideEffect.NavigateHome(sheetsIds = state.sheetIds, cellModel = cellModel))
    }

    fun intentGetLocalUserToken() = intent {
        getLocalUserTokenUseCase().collect {
            Logger.d(it.toString())

            if (it == null) {
                postSideEffect(MainSideEffect.NavigateLogin)
            } else {
                val sheets = getMandalartSheetsUseCase(it.accessToken)
                when (sheets) {
                    is Result.Success -> {
                        if (sheets.data.isEmpty()) {
                            val sheetId = createMandalartSheetUseCase(it.accessToken)

                            when (sheetId) {
                                is Result.Success -> {
                                    reduce {
                                        state.copy(sheetIds = listOf(sheetId.data))
                                    }
                                    postSideEffect(MainSideEffect.NavigateHome(listOf(sheetId.data)))
                                }

                                is Result.Failure -> {
                                    // TODO: 에러 로직 처리 필요
                                }

                                is Result.Exception -> {
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

                    is Result.Failure -> {
                        // TODO: 에러 로직 필요
                    }

                    is Result.Exception -> {
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