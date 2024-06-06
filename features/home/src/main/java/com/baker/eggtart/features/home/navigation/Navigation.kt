package com.baker.eggtart.features.home.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.baker.eggtart.common.feature.routes.root.RootRoutes
import com.baker.eggtart.common.util.Logger
import com.baker.eggtart.domain.mandalart.model.CellModel
import com.baker.eggtart.domain.mandalart.model.CellTodosModel
import com.baker.eggtart.features.home.HomeScreen
import kotlinx.serialization.json.Json

/**
 *  Created by wonjin on 2024/04/09
 **/

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.homeScreen(
    navigateWriteGoal: (CellModel) -> Unit,
    navSharedTransitionScope: SharedTransitionScope
) {
    composable(route = RootRoutes.HOME, listOf(navArgument("sheetIds") {
        defaultValue = ""
    }, navArgument("cellModel") {
        defaultValue = null
        nullable = true
    })) { backStackEntry ->
        val sheetIds = backStackEntry.arguments?.getString("sheetIds")?.split(",")?.mapNotNull { it.toLongOrNull() } ?: listOf()
        val cellModel = backStackEntry.arguments?.getString("cellModel")?.let {
            Json.decodeFromString<CellTodosModel>(it)
        }

        Logger.d("homescreen cellModel: $cellModel")
        HomeScreen(navigateWriteGoal, cellModel = cellModel, sheetIds = sheetIds, navSharedTransitionScope = navSharedTransitionScope, navAnimatedVisibilityScope = this@composable)
    }
}