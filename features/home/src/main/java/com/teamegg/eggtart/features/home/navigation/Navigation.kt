package com.teamegg.eggtart.features.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.teamegg.eggtart.common.feature.routes.root.RootRoutes
import com.teamegg.eggtart.domain.mandalart.model.ResCellModel
import com.teamegg.eggtart.domain.mandalart.model.ResCellTodosModel
import com.teamegg.eggtart.features.home.HomeScreen
import kotlinx.serialization.json.Json

/**
 *  Created by wonjin on 2024/04/09
 **/

fun NavGraphBuilder.homeScreen(
    navigateWriteGoal: (ResCellModel) -> Unit
) {
    composable(
        route = RootRoutes.HOME, listOf(navArgument("sheetIds") {
            defaultValue = ""
        }, navArgument("cellModel") {
            defaultValue = null
            nullable = true
        })
    ) { backStackEntry ->
        val sheetIds = backStackEntry.arguments?.getString("sheetIds")?.split(",")?.mapNotNull { it.toLongOrNull() } ?: listOf()
        val cellModel = backStackEntry.arguments?.getString("cellModel")?.let {
            Json.decodeFromString<ResCellTodosModel>(it)
        }
        HomeScreen(navigateWriteGoal, cellModel = cellModel, sheetIds = sheetIds)
    }
}