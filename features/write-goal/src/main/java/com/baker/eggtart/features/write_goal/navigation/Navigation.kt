package com.baker.eggtart.features.write_goal.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.baker.eggtart.common.feature.routes.root.RootRoutes
import com.baker.eggtart.domain.mandalart.model.CellModel
import com.baker.eggtart.domain.mandalart.model.CellTodosModel
import com.baker.eggtart.features.write_goal.WriteGoalScreen
import kotlinx.serialization.json.Json

/**
 *  Created by wonjin on 2024/04/09
 **/

fun NavGraphBuilder.writeGoalScreen(navigateHome: (CellTodosModel?) -> Unit) {
    composable(RootRoutes.WRITE_GOAL, listOf(navArgument("cellModel") {})) { backStackEntry ->
        val cellModel = Json.decodeFromString<CellModel>(backStackEntry.arguments?.getString("cellModel") ?: "")
        WriteGoalScreen(navigateHome, cellModel)
    }
}