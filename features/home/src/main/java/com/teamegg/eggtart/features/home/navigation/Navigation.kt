package com.teamegg.eggtart.features.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.teamegg.eggtart.common.feature.routes.root.RootRoutes
import com.teamegg.eggtart.domain.mandalart.model.CellModel
import com.teamegg.eggtart.features.home.HomeScreen

/**
 *  Created by wonjin on 2024/04/09
 **/

fun NavGraphBuilder.homeScreen(
    navigateWriteGoal: (CellModel) -> Unit
) {
    composable(route = RootRoutes.HOME, listOf(navArgument("sheetIds") {
        defaultValue = ""
    })) { backStackEntry ->
        val sheetIds = backStackEntry.arguments?.getString("sheetIds")?.split(",")?.mapNotNull { it.toLongOrNull() } ?: listOf()
        HomeScreen(navigateWriteGoal, sheetIds)
    }
}