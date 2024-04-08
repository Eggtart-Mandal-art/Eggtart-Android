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
import androidx.navigation.compose.rememberNavController
import com.teamegg.eggtart.common.feature.navigation.root.Graph
import com.teamegg.eggtart.common.feature.navigation.root.RootGraph
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
            com.teamegg.eggtart.common.feature.theme.EggtartTheme {
                Surface(
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Vertical))
                        .imePadding()
                ) {
                    RootGraph(navHostController = rememberNavController(), startDestination = Graph.HOME)
                }
            }
        }
    }
}