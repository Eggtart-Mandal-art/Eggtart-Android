package com.teamegg.eggtart.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.teamegg.eggtart.common.feature.routes.root.RootRoutes
import com.teamegg.eggtart.common.feature.theme.EggtartTheme
import com.teamegg.eggtart.features.home.navigation.homeScreen
import com.teamegg.eggtart.features.write_goal.navigation.writeGoalScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Created by wonjin on 2024/04/08
 **/

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {
            EggtartTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Vertical))
                        .imePadding()
                ) {
                    NavHost(navController = navController, startDestination = RootRoutes.HOME, route = RootRoutes.ROOT) {
                        homeScreen(
                            navigateWriteGoal = { index ->
                                navController.navigate(RootRoutes.WRITE_GOAL)
                            }
                        )
                        writeGoalScreen(navController)
                    }
                }
            }
        }
    }
}