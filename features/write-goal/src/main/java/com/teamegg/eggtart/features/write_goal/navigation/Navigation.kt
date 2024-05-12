package com.teamegg.eggtart.features.write_goal.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.teamegg.eggtart.common.feature.routes.root.RootRoutes
import com.teamegg.eggtart.domain.mandalart.model.CellModel
import com.teamegg.eggtart.domain.mandalart.model.CellTodosModel
import com.teamegg.eggtart.features.write_goal.WriteGoalScreen
import kotlinx.serialization.json.Json

/**
 *  Created by wonjin on 2024/04/09
 **/

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.writeGoalScreen(
    navigateHome: (CellTodosModel?) -> Unit,
    navSharedTransitionScope: SharedTransitionScope,
) {
    composable(RootRoutes.WRITE_GOAL, listOf(navArgument("cellModel") {})) { backStackEntry ->
        val cellModel = Json.decodeFromString<CellModel>(backStackEntry.arguments?.getString("cellModel") ?: "")
        WriteGoalScreen(navigateHome, navSharedTransitionScope = navSharedTransitionScope, navAnimatedVisibilityScope = this@composable, cellModel = cellModel)
    }
}