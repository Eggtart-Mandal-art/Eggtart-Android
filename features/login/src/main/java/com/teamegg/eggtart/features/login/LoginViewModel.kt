package com.teamegg.eggtart.features.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/11
 **/

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    fun login() {
//        viewModelScope.launch {
//
//            val result = kakaoLoginUseCase()
//                .getOrElse {
//                    Logger.d("failure: $it")
//                }
//
//            Logger.d("result: $result")
//        }
    }
}