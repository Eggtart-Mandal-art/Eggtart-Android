package com.teamegg.eggtart.features.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.teamegg.eggtart.common.feature.routes.home.HomeRoutes
import com.teamegg.eggtart.common.feature.routes.root.RootRoutes
import com.teamegg.eggtart.features.home.calendar.CalendarScreen
import com.teamegg.eggtart.features.home.components.HomeBottomBar
import com.teamegg.eggtart.features.home.mandalart.MandalartScreen
import com.teamegg.eggtart.features.home.settings.SettingsScreen

/**
 *  Created by wonjin on 2024/04/04
 **/

@Composable
fun HomeScreen(navigateWriteGoal: (Int) -> Unit, navHostController: NavHostController = rememberNavController()) {

    Scaffold(
        bottomBar = {
            HomeBottomBar(navHostController = navHostController)
        }
    ) { paddingValues ->
        NavHost(contentAlignment = Alignment.TopStart, navController = navHostController, startDestination = HomeRoutes.Mandalart.route, route = RootRoutes.HOME) {
            composable(HomeRoutes.Mandalart.route) {
                MandalartScreen(navigateWriteGoal = navigateWriteGoal, homePaddingValues = paddingValues)
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