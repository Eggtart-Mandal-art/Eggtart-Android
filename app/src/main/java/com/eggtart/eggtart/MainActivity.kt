package com.eggtart.eggtart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.eggtart.eggtart.common.ui.EggtartTheme
import com.eggtart.eggtart.common.ui.Typography
import com.eggtart.eggtart.features.goal.write.WriteGoalScreen
import com.eggtart.eggtart.features.home.calendar.CalendarScreen
import com.eggtart.eggtart.features.home.mandalart.MandalartScreen
import com.eggtart.eggtart.features.home.settings.SettingsScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Created by wonjin on 2024/03/21
 **/

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            EggtartTheme {
                val items = remember { listOf(BottomBarRoutes.Mandalart, BottomBarRoutes.Calendar, BottomBarRoutes.Settings) }
                val navController = rememberNavController()

                val showHomeBottomBar = navController.currentBackStackEntryAsState().value?.destination?.route in items.map { it.route }

                Scaffold(
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Vertical))
                        .imePadding(),
                    bottomBar = {
                        AnimatedVisibility(
                            visible = showHomeBottomBar,
                            enter = slideInVertically(animationSpec = tween(100)) { it / 2 } + fadeIn(animationSpec = tween(100)),
                            exit = slideOutVertically(animationSpec = tween(100)) { it / 2 } + fadeOut(animationSpec = tween(100))
                        ) {
                            HomeBottomBar(navController, items)
                        }
                    }) { paddingValues ->
                    Surface(modifier = Modifier.padding(paddingValues)) {
                        NavHost(navController = navController, startDestination = ScreenRoutes.Home.route) {
                            navigation(startDestination = BottomBarRoutes.Mandalart.route, route = ScreenRoutes.Home.route) {
                                composable(BottomBarRoutes.Mandalart.route) {
                                    MandalartScreen(navController)
                                }
                                composable(BottomBarRoutes.Calendar.route) {
                                    CalendarScreen()
                                }
                                composable(BottomBarRoutes.Settings.route) {
                                    SettingsScreen()
                                }
                            }

                            composable(ScreenRoutes.WriteGoal.route) {
                                WriteGoalScreen(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeBottomBar(navHostController: NavHostController, items: List<BottomBarRoutes>) {
    Column {
        HorizontalDivider()
        NavigationBar(containerColor = MaterialTheme.colorScheme.background, tonalElevation = 0.dp) {
            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach {
                Column(modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
                    .clickable(
                        interactionSource = remember {
                            MutableInteractionSource()
                        },
                        indication = rememberRipple(bounded = false)
                    ) {
                        navHostController.navigate(it.route) {
                            launchSingleTop = true

                            popUpTo(BottomBarRoutes.Mandalart.route)
                        }
                    }
                    .padding(top = 12.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                    Icon(
                        painter = painterResource(id = if (currentRoute == it.route) it.selectedIconId else it.unselectedIconId),
                        contentDescription = stringResource(id = it.labelId),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(id = it.labelId),
                        style = Typography.labelSmall,
                        color = if (currentRoute == it.route) MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f) else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
                    )
                }
            }
        }
    }
}

sealed class ScreenRoutes(val route: String) {
    data object Home : ScreenRoutes("home")
    data object WriteGoal : ScreenRoutes("writeGoal")
}

sealed class BottomBarRoutes(@StringRes val labelId: Int, @DrawableRes val unselectedIconId: Int, @DrawableRes val selectedIconId: Int, val route: String) {
    data object Mandalart : BottomBarRoutes(R.string.nav_mandalart, R.drawable.ic_grid_n, R.drawable.ic_grid_s, "mandalart")
    data object Calendar : BottomBarRoutes(R.string.nav_calendar, R.drawable.ic_calendar_n, R.drawable.ic_calendar_s, "calendar")
    data object Settings : BottomBarRoutes(R.string.nav_settings, R.drawable.ic_settings_n, R.drawable.ic_settings_s, "settings")
}