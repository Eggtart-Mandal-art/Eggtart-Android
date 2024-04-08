package com.teamegg.eggtart.features.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.teamegg.eggtart.common.feature.navigation.home.HomeGraph
import com.teamegg.eggtart.features.home.components.HomeBottomBar

/**
 *  Created by wonjin on 2024/04/04
 **/

@Composable
fun HomeScreen(navHostController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            HomeBottomBar(navHostController = navHostController)
        }
    ) { paddingValues ->
        HomeGraph(navHostController = navHostController, paddingValues = paddingValues)
    }
}