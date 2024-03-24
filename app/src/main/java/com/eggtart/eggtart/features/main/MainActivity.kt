package com.eggtart.eggtart.features.main

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eggtart.eggtart.R
import com.eggtart.eggtart.common.BaseActivity
import com.eggtart.eggtart.common.ui.EggtartTheme
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
        Text(text = "에그타르트")
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

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach {
            NavigationBarItem(
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
                        modifier = Modifier.alpha(if (currentRoute == it.screenRoute) 0.7f else 0.25f),
                        painter = painterResource(id = it.iconId),
                        contentDescription = stringResource(id = it.labelId)
                    )
                },
                label = {
                    Text(text = stringResource(id = it.labelId))
                })
        }
    }
}

@Composable
private fun MainBody(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Mandalart.screenRoute) {
        composable(BottomNavItem.Mandalart.screenRoute) {

        }
        composable(BottomNavItem.Calendar.screenRoute) {

        }
        composable(BottomNavItem.Timer.screenRoute) {

        }
        composable(BottomNavItem.Settings.screenRoute) {

        }
    }
}


sealed class BottomNavItem(@StringRes val labelId: Int, @DrawableRes val iconId: Int, val screenRoute: String) {
    object Mandalart : BottomNavItem(R.string.nav_mandalart, R.drawable.ic_mandalart_n, "/Mandalart")
    object Calendar : BottomNavItem(R.string.nav_calendar, R.drawable.ic_calendar_n, "/Calendar")
    object Timer : BottomNavItem(R.string.nav_timer, R.drawable.ic_timer_n, "/Timer")
    object Settings : BottomNavItem(R.string.nav_settings, R.drawable.ic_settings_n, "/Settings")
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EggtartTheme {
        Scaffold(
            topBar = { MainAppBar() },
            bottomBar = { BottomNavBar(rememberNavController()) },
        ) {
            MainBody(rememberNavController())
        }
    }
}