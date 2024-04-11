package com.teamegg.eggtart.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teamegg.eggtart.domain.kakao.usecase.KakaoLoginUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

/**
 *  Created by wonjin on 2024/03/21
 **/

class MainViewModel @AssistedInject constructor(
    @Assisted private val kakaoLoginUseCase: KakaoLoginUseCase
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {

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

    override val container = container<MainState, MainSideEffect>(MainState())
}