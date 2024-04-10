package com.teamegg.eggtart.features.write_goal.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.teamegg.eggtart.common.feature.routes.root.RootRoutes
import com.teamegg.eggtart.features.write_goal.WriteGoalScreen

/**
 *  Created by wonjin on 2024/04/09
 **/

fun NavGraphBuilder.writeGoalScreen(navHostController: NavHostController) {
    composable(RootRoutes.WRITE_GOAL) {
        WriteGoalScreen(navHostController)
    }
}