package com.teamegg.eggtart.features.login

import androidx.lifecycle.ViewModel
import com.teamegg.eggtart.common.feature.components.ServerErrorDialogData
import com.teamegg.eggtart.common.util.ServerResult
import com.teamegg.eggtart.domain.user.usecase.GetUserInfoUseCase
import com.teamegg.eggtart.domain.user.usecase.LoginWithKakaoUseCase
import com.teamegg.eggtart.domain.user.usecase.SetLocalUserInfoUseCase
import com.teamegg.eggtart.domain.user.usecase.SetLocalUserTokenUseCase
import com.teamegg.eggtart.features.login.sideeffect.LoginSideEffect
import com.teamegg.eggtart.features.login.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/11
 **/

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithKakaoUseCase: LoginWithKakaoUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val setLocalUserTokenUseCase: SetLocalUserTokenUseCase,
    private val setLocalUserInfoUseCase: SetLocalUserInfoUseCase
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {
    override val container: Container<LoginState, LoginSideEffect> = container(LoginState())

    fun intentLoginWithKakao(accessToken: String) = intent {

        reduce {
            state.copy(loginLoading = true)
        }

        val loginResponse = loginWithKakaoUseCase(accessToken)

        if (loginResponse is ServerResult.Success) {
            val userInfoResponse = getUserInfoUseCase(loginResponse.data.accessToken)

            if (userInfoResponse is ServerResult.Success) {
                setLocalUserInfoUseCase(userInfoResponse.data)
                setLocalUserTokenUseCase(loginResponse.data)
            } else {
                postSideEffect(LoginSideEffect.ServerErrorPopup(userInfoResponse))
            }
        } else {
            postSideEffect(LoginSideEffect.ServerErrorPopup(loginResponse))
        }

        reduce {
            state.copy(loginLoading = false)
        }
    }

    fun intentSetServerErrorData(serverErrorDialogData: ServerErrorDialogData?) = intent {
        reduce {
            state.copy(serverErrorDialogData = serverErrorDialogData)
        }
    }
}