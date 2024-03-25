package com.eggtart.eggtart.features.main

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eggtart.eggtart.R
import com.eggtart.eggtart.common.BaseActivity
import com.eggtart.eggtart.common.ui.EggtartTheme
import com.eggtart.eggtart.features.main.screens.calendar.CalendarScreen
import com.eggtart.eggtart.features.main.screens.mandalart.MandalartScreen
import com.eggtart.eggtart.features.main.screens.settings.SettingsScreen
import com.eggtart.eggtart.features.main.screens.timer.TimerScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Created by wonjin on 2024/03/21
 **/

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun initData() {}

    @Composable
    override fun AppBar() = MainAppBar()

    @Composable
    override fun Body(navHostController: NavHostController) = MainBody(navHostController)

    @Composable
    override fun NavigationBar(navHostController: NavHostController) = BottomNavBar(navHostController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainAppBar() {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.titleLarge)
    }, actions = {
        Icon(imageVector = Icons.Filled.Star, contentDescription = "")
    })
}

@Composable
private fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Mandalart,
        BottomNavItem.Calendar,
        BottomNavItem.Timer,
        BottomNavItem.Settings
    )

    Column {
        HorizontalDivider()
        NavigationBar(containerColor = MaterialTheme.colorScheme.background, tonalElevation = 0.dp) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach {
                NavigationBarItem(
                    modifier = Modifier.clip(RoundedCornerShape(24.dp)),
                    selected = currentRoute == it.screenRoute,
                    onClick = {
                        navController.navigate(it.screenRoute) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = it.iconId),
                            contentDescription = stringResource(id = it.labelId)
                        )
                    },
                    label = {
                        Text(text = stringResource(id = it.labelId))
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                        selectedTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                        selectedIndicatorColor = Color.Transparent,
                        unselectedIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f),
                        unselectedTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f),
                        disabledIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                        disabledTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
                    )
                )
            }
        }
    }
}

@Composable
private fun MainBody(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Mandalart.screenRoute) {
        composable(BottomNavItem.Mandalart.screenRoute) {
            MandalartScreen()
        }
        composable(BottomNavItem.Calendar.screenRoute) {
            CalendarScreen()
        }
        composable(BottomNavItem.Timer.screenRoute) {
            TimerScreen()
        }
        composable(BottomNavItem.Settings.screenRoute) {
            SettingsScreen()
        }
    }
}


sealed class BottomNavItem(@StringRes val labelId: Int, @DrawableRes val iconId: Int, val screenRoute: String) {
    data object Mandalart : BottomNavItem(R.string.nav_mandalart, R.drawable.ic_mandalart_n, "/Mandalart")
    data object Calendar : BottomNavItem(R.string.nav_calendar, R.drawable.ic_calendar_n, "/Calendar")
    data object Timer : BottomNavItem(R.string.nav_timer, R.drawable.ic_timer_n, "/Timer")
    data object Settings : BottomNavItem(R.string.nav_settings, R.drawable.ic_settings_n, "/Settings")
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EggtartTheme {
        val navController = rememberNavController()
        Scaffold(
            topBar = { MainAppBar() },
            bottomBar = { BottomNavBar(navController) },
        ) {
            MainBody(navController)
        }
    }
}