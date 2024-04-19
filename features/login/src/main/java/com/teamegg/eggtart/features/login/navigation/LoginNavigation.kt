package com.teamegg.eggtart.features.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.teamegg.eggtart.common.feature.routes.root.RootRoutes
import com.teamegg.eggtart.features.login.LoginScreen

/**
 * Created by 노원진 on 2024.04.10
 */

fun NavGraphBuilder.loginScreen(
    startKakaoLogin: suspend () -> Unit,
) {
    composable(RootRoutes.LOGIN, arguments = listOf(navArgument("kakaoAccessToken") {
        nullable = true
        defaultValue = null
        type = NavType.StringType
    })) { backStackEntry ->
        val kakaoAccessToken = backStackEntry.arguments?.getString("kakaoAccessToken")

        LoginScreen(kakaoAccessToken = kakaoAccessToken, startKakaoLogin = startKakaoLogin)
    }
}