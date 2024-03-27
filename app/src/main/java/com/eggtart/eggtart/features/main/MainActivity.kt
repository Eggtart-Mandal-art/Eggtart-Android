package com.eggtart.eggtart.features.main

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eggtart.eggtart.R
import com.eggtart.eggtart.common.BaseActivity
import com.eggtart.eggtart.common.ui.EggtartTheme
import com.eggtart.eggtart.common.ui.Typography
import com.eggtart.eggtart.features.main.screens.calendar.CalendarScreen
import com.eggtart.eggtart.features.main.screens.mandalart.MandalartScreen
import com.eggtart.eggtart.features.main.screens.settings.SettingsScreen
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
    TopAppBar(
        modifier = Modifier.height(56.dp), title = {
            Text(modifier = Modifier.padding(vertical = 16.dp), text = stringResource(id = R.string.app_name), fontSize = 22.sp, lineHeight = 24.sp)
        }, actions = {
            IconButton(modifier = Modifier
                .padding(all = 8.dp)
                .size(40.dp), onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_star_n), contentDescription = "")
            }
        }
    )
}

@Composable
private fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Mandalart,
        BottomNavItem.Calendar,
        BottomNavItem.Settings
    )

    Column {
        HorizontalDivider()
        NavigationBar(containerColor = MaterialTheme.colorScheme.background, tonalElevation = 0.dp) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
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
                        navController.navigate(it.screenRoute) {
                            launchSingleTop = true

                            popUpTo(BottomNavItem.Mandalart.screenRoute)
                        }
                    }
                    .padding(top = 12.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                    Icon(
                        painter = painterResource(id = if (currentRoute == it.screenRoute) it.selectedIconId else it.unselectedIconId),
                        contentDescription = stringResource(id = it.labelId),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(id = it.labelId),
                        style = Typography.labelSmall,
                        color = if (currentRoute == it.screenRoute) MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f) else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
                    )
                }
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
        composable(BottomNavItem.Settings.screenRoute) {
            SettingsScreen()
        }
    }
}


sealed class BottomNavItem(@StringRes val labelId: Int, @DrawableRes val unselectedIconId: Int, @DrawableRes val selectedIconId: Int, val screenRoute: String) {
    data object Mandalart : BottomNavItem(R.string.nav_mandalart, R.drawable.ic_grid_n, R.drawable.ic_grid_s, "/Mandalart")
    data object Calendar : BottomNavItem(R.string.nav_calendar, R.drawable.ic_calendar_n, R.drawable.ic_calendar_s, "/Calendar")
    data object Settings : BottomNavItem(R.string.nav_settings, R.drawable.ic_settings_n, R.drawable.ic_settings_s, "/Settings")
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