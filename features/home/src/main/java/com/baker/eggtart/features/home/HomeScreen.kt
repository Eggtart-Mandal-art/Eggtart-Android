package com.baker.eggtart.features.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.baker.eggtart.common.feature.routes.home.HomeRoutes
import com.baker.eggtart.common.feature.routes.root.RootRoutes
import com.baker.eggtart.domain.mandalart.model.CellModel
import com.baker.eggtart.domain.mandalart.model.CellTodosModel
import com.baker.eggtart.features.home.calendar.CalendarScreen
import com.baker.eggtart.features.home.components.HomeBottomBar
import com.baker.eggtart.features.home.mandalart.MandalartScreen
import com.baker.eggtart.features.home.settings.SettingsScreen

/**
 *  Created by wonjin on 2024/04/04
 **/

@Composable
fun HomeScreen(
    navigateWriteGoal: (CellModel) -> Unit,
    sheetIds: List<Long>,
    cellModel: CellTodosModel?,
    navHostController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            HomeBottomBar(navHostController = navHostController)
        }
    ) { paddingValues ->
        NavHost(contentAlignment = Alignment.TopStart, navController = navHostController, startDestination = HomeRoutes.Mandalart.route, route = RootRoutes.HOME) {
            composable(HomeRoutes.Mandalart.route) {
                MandalartScreen(
                    navigateWriteGoal = navigateWriteGoal,
                    sheetIds = sheetIds,
                    cellModel = cellModel,
                    homePaddingValues = paddingValues)
            }

            composable(HomeRoutes.Calendar.route) {
                CalendarScreen(homePaddingValues = paddingValues)
            }

            composable(HomeRoutes.Settings.route) {
                SettingsScreen(homePaddingValues = paddingValues)
            }
        }
    }
}