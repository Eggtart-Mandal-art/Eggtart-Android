package com.teamegg.eggtart.features.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.teamegg.eggtart.common.feature.routes.root.RootRoutes
import com.teamegg.eggtart.features.home.HomeScreen

/**
 *  Created by wonjin on 2024/04/09
 **/

fun NavGraphBuilder.homeScreen(
    navigateWriteGoal: (Int) -> Unit
) {
    composable(route = RootRoutes.HOME) {
        HomeScreen(navigateWriteGoal)
    }
}