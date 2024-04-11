package com.teamegg.eggtart.features.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.kakao.sdk.auth.model.OAuthToken
import com.teamegg.eggtart.common.feature.routes.root.RootRoutes
import com.teamegg.eggtart.features.login.LoginScreen

/**
 * Created by 노원진 on 2024.04.10
 */

fun NavGraphBuilder.loginScreen(
    startKakaoLogin: suspend () -> Unit,
    navigateHomeScreen: () -> Unit
) {
    composable(RootRoutes.LOGIN) { backStackEntry ->
        val tokenDataString = backStackEntry.arguments?.getString("tokenData")
        val tokenData = Gson().fromJson(tokenDataString, OAuthToken::class.java)

        LoginScreen(tokenData, startKakaoLogin, navigateHomeScreen)
    }
}