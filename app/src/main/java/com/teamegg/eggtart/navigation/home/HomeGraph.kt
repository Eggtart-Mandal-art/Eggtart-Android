package com.teamegg.eggtart.navigation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.teamegg.eggtart.R
import com.teamegg.eggtart.features.goal.write.WriteGoalScreen
import com.teamegg.eggtart.features.home.calendar.CalendarScreen
import com.teamegg.eggtart.features.home.mandalart.MandalartScreen
import com.teamegg.eggtart.features.home.settings.SettingsScreen
import com.teamegg.eggtart.navigation.root.Graph

/**
 *  Created by wonjin on 2024/04/04
 **/

@Composable
fun HomeGraph(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(contentAlignment = Alignment.TopStart, navController = navHostController, startDestination = HomeRoute.Mandalart.route, route = Graph.HOME) {
        composable(HomeRoute.Mandalart.route) {
            MandalartScreen(navHostController = navHostController, homePaddingValues = paddingValues)
        }

        composable(HomeRoute.Calendar.route) {
            CalendarScreen(homePaddingValues = paddingValues)
        }

        composable(HomeRoute.Settings.route) {
            SettingsScreen(homePaddingValues = paddingValues)
        }

        composable(HomeRoute.WriteGoal.route) {
            WriteGoalScreen(navHostController = navHostController)
        }
    }
}

sealed class HomeRoute(val route: String, @StringRes val labelId: Int = -1, @DrawableRes val unselectedIconId: Int = -1, @DrawableRes val selectedIconId: Int = -1) {
    data object Mandalart : HomeRoute("mandalart", R.string.nav_mandalart, R.drawable.ic_grid_n, R.drawable.ic_grid_s)
    data object Calendar : HomeRoute("calendar", R.string.nav_calendar, R.drawable.ic_calendar_n, R.drawable.ic_calendar_s)
    data object Settings : HomeRoute("settings", R.string.nav_settings, R.drawable.ic_settings_n, R.drawable.ic_settings_s)
    data object WriteGoal : HomeRoute("writeGoal", R.string.nav_settings, R.drawable.ic_settings_n, R.drawable.ic_settings_s)
}