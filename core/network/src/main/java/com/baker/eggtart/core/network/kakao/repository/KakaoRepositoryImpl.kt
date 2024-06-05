package com.baker.eggtart.core.network.kakao.repository

import android.content.Context
import com.kakao.sdk.common.model.AuthError
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.baker.eggtart.common.util.Logger
import com.baker.eggtart.domain.kakao.repository.KakaoRepository
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume


/**
 *  Created by wonjin on 2024/04/11
 **/
class KakaoRepositoryImpl @Inject constructor(@ActivityContext private val context: Context) : KakaoRepository {
    override suspend fun loginWithKakao(): Result<String?> {

        return if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            loginWithKakaoTalk()
        } else {
            loginWithKakaoAccount()
        }
    }

    private suspend fun loginWithKakaoTalk(): Result<String?> {
        return suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    if ((error is ClientError && error.reason == ClientErrorCause.Cancelled) || (error is AuthError && error.reason == AuthErrorCause.AccessDenied)) {
                        UserApiClient.instance.loginWithKakaoAccount(context) { accountToken, accountError ->
                            if (accountError != null) {
                                continuation.resume(Result.failure(accountError))
                            } else {
                                continuation.resume(Result.success(accountToken?.accessToken))
                            }
                        }
                    } else {
                        continuation.resume(Result.failure(error))
                        Logger.d("error: $error")
                    }
                } else {
                    continuation.resume(Result.success(token?.accessToken))
                }
            }
        }
    }

    private suspend fun loginWithKakaoAccount(): Result<String?> {
        return suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                if (error != null) {
                    continuation.resume(Result.failure(error))
                } else {
                    continuation.resume(Result.success(token?.accessToken))
                }
            }
        }
    }
}
