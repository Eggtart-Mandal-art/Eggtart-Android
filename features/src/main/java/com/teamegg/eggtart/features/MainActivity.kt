package com.teamegg.eggtart.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.teamegg.eggtart.common.feature.routes.root.RootRoutes
import com.teamegg.eggtart.common.feature.theme.EggtartTheme
import com.teamegg.eggtart.domain.kakao.usecase.KakaoLoginUseCase
import com.teamegg.eggtart.features.home.navigation.homeScreen
import com.teamegg.eggtart.features.login.navigation.loginScreen
import com.teamegg.eggtart.features.write_goal.navigation.writeGoalScreen
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectSideEffect
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/08
 **/

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var mainViewModelFactory: MainViewModel.MainViewModelFactory

    @Inject
    lateinit var kakaoLoginUseCase: KakaoLoginUseCase

    private val viewModel by viewModels<MainViewModel> {
        MainViewModel.create(mainViewModelFactory, kakaoLoginUseCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            EggtartTheme {
                val navController = rememberNavController()
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

                if (currentRoute == RootRoutes.LOGIN) {
                    val color = Color.Transparent.toArgb()

                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.dark(scrim = color),
                        navigationBarStyle = SystemBarStyle.dark(scrim = color)
                    )
                } else {
                    val color = MaterialTheme.colorScheme.background.toArgb()

                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.light(
                            scrim = color,
                            darkScrim = color,
                        ),
                        navigationBarStyle = SystemBarStyle.light(
                            scrim = color,
                            darkScrim = color
                        )
                    )
                }

                Surface(
                    modifier = Modifier
                        .windowInsetsPadding(if (currentRoute == RootRoutes.LOGIN) WindowInsets(top = 0.dp, bottom = 0.dp) else WindowInsets.systemBars.only(WindowInsetsSides.Vertical))
                        .imePadding()
                ) {
                    NavHost(navController = navController, startDestination = RootRoutes.LOGIN, route = RootRoutes.ROOT) {
                        homeScreen(
                            navigateWriteGoal = { index ->
                                navController.navigate(RootRoutes.WRITE_GOAL)
                            }
                        )
                        writeGoalScreen(navController)
                        loginScreen(
                            startKakaoLogin = {
                                viewModel.intentKakaoLogin()
                            }
                        ) {
                            navController.navigate(RootRoutes.HOME) {
                                popUpTo(RootRoutes.LOGIN) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }

                viewModel.collectSideEffect {
                    when (it) {
                        is MainSideEffect.NavigateLoginWithKakaoResult -> {
                            navController.navigate(RootRoutes.LOGIN.replace("{tokenData}", Gson().toJson(it.kakaoLoginData.getOrNull()))) {
                                launchSingleTop = true
                            }
                        }
                    }
                }
            }
        }
    }
}