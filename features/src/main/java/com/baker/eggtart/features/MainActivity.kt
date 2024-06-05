package com.baker.eggtart.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baker.eggtart.common.feature.components.EggtartServerErrorPopup
import com.baker.eggtart.common.feature.components.ServerErrorDialogData
import com.baker.eggtart.common.feature.routes.root.RootRoutes
import com.baker.eggtart.common.feature.theme.EggtartTheme
import com.baker.eggtart.common.util.Logger
import com.baker.eggtart.domain.kakao.usecase.KakaoLoginUseCase
import com.baker.eggtart.features.home.navigation.homeScreen
import com.baker.eggtart.features.login.navigation.loginScreen
import com.baker.eggtart.features.write_goal.navigation.writeGoalScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.orbitmvi.orbit.compose.collectAsState
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

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                Logger.d("${viewModel.container.stateFlow.value.initialized}")
                !viewModel.container.stateFlow.value.initialized
            }
        }

        setContent {
            EggtartTheme {
                val viewModelState = viewModel.collectAsState().value
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
                    NavHost(navController = navController, startDestination = RootRoutes.SPLASH, route = RootRoutes.ROOT) {
                        composable(RootRoutes.SPLASH) {
                            Box(modifier = Modifier.fillMaxSize())

                            LaunchedEffect(Unit) {
                                viewModel.intentGetLocalUserToken()
                            }
                        }
                        homeScreen(
                            navigateWriteGoal = viewModel::intentNavigateWriteGoal
                        )
                        writeGoalScreen(navigateHome = viewModel::intentNavigateHome)
                        loginScreen(
                            startKakaoLogin = viewModel::intentKakaoLogin,
                        )
                    }
                }

                if (viewModelState.serverErrorDialogData != null) {
                    EggtartServerErrorPopup(serverErrorDialogData = viewModelState.serverErrorDialogData)
                }

                viewModel.collectSideEffect { sideEffect ->
                    when (sideEffect) {
                        is MainSideEffect.NavigateLoginWithKakaoResult -> {
                            navController.navigate(RootRoutes.LOGIN.replace("{kakaoAccessToken}", sideEffect.kakaoAccessToken.getOrNull() ?: "{kakaoAccessToken}")) {
                                launchSingleTop = true
                            }
                        }

                        is MainSideEffect.NavigateLogin -> {
                            navController.navigate(RootRoutes.LOGIN) {
                                navController.currentDestination?.route?.let {
                                    popUpTo(it) { inclusive = true }
                                }
                            }
                        }

                        is MainSideEffect.NavigateHome -> {
                            Logger.d("navigateHome cell: ${sideEffect.cellModel}")
                            navController.navigate(
                                RootRoutes.HOME
                                    .replace("{sheetIds}", sideEffect.sheetsIds.joinToString(","))
                                    .replace("{cellModel}", Json.encodeToString(sideEffect.cellModel))
                            ) {
                                launchSingleTop = true

                                navController.currentDestination?.route?.let {
                                    popUpTo(it) { inclusive = true }
                                }
                            }
                        }

                        is MainSideEffect.NavigateWriteGoal -> {
                            navController.navigate(RootRoutes.WRITE_GOAL.replace("{cellModel}", Json.encodeToString(sideEffect.cellModel)))
                        }

                        is MainSideEffect.ServerErrorPopup -> {
                            when (sideEffect.type) {
                                ServerCallType.GET_MANDALART_SHEET, ServerCallType.CREATE_MANDALART_SHEET -> {
                                    viewModel.intentSetServerErrorData(
                                        ServerErrorDialogData(
                                            serverResult = sideEffect.serverResult,
                                            onConfirm = {
                                                finish()
                                            },
                                            onDismiss = {
                                                viewModel.intentSetServerErrorData(null)
                                            },
                                            onClearLoginData = {
                                                viewModel.intentClearLoginData()
                                            }
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}