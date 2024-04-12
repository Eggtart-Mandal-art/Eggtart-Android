package com.teamegg.eggtart.features.login

import androidx.lifecycle.ViewModel
import com.teamegg.eggtart.common.util.Logger
import com.teamegg.eggtart.common.util.Result
import com.teamegg.eggtart.domain.user.usecase.LoginWithKakaoUseCase
import com.teamegg.eggtart.features.login.sideeffect.LoginSideEffect
import com.teamegg.eggtart.features.login.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/11
 **/

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithKakaoUseCase: LoginWithKakaoUseCase
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {
    override val container: Container<LoginState, LoginSideEffect> = container(LoginState())

    fun intentLoginWithKakao(accessToken: String) = intent {

        reduce {
            state.copy(loginLoading = true)
        }

        val response = loginWithKakaoUseCase(accessToken)

        if (response is Result.Success) {
            Logger.d("result: ${response.data}")
        } else if (response is Result.Failure) {
            Logger.d("failed result: ${response.error}")
        }

        reduce {
            state.copy(loginLoading = false)
        }
    }
}