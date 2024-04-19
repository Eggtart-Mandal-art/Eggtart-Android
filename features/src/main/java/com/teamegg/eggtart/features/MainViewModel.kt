package com.teamegg.eggtart.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teamegg.eggtart.common.util.Logger
import com.teamegg.eggtart.domain.kakao.usecase.KakaoLoginUseCase
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
    private val getLocalUserTokenUseCase: GetLocalUserTokenUseCase
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

    init {
        intentGetLocalUserToken()
    }

    fun intentKakaoLogin() = intent {
        postSideEffect(MainSideEffect.NavigateLoginWithKakaoResult(kakaoLoginUseCase()))
    }

    fun navigateWriteGoal(goalIndex: Int) = intent {
        postSideEffect(MainSideEffect.NavigateWriteGoal(goalIndex))
    }

    fun navigateHome() = intent {
        postSideEffect(MainSideEffect.NavigateHome)
    }

    private fun intentGetLocalUserToken() = intent {
        getLocalUserTokenUseCase().collect {
            Logger.d(it.toString())

            if (it == null) {
                postSideEffect(MainSideEffect.NavigateLogin)
            } else {
                postSideEffect(MainSideEffect.NavigateHome)
            }

            reduce {
                state.copy(true)
            }
        }
    }
}