package com.teamegg.eggtart.features.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.teamegg.eggtart.common.feature.routes.root.RootRoutes
import com.teamegg.eggtart.features.login.LoginScreen

/**
 * Created by 노원진 on 2024.04.10
 */

fun NavGraphBuilder.loginScreen(
    navigateHomeScreen: () -> Unit
) {
    composable(RootRoutes.LOGIN) {
        LoginScreen(navigateHomeScreen)
    }
}