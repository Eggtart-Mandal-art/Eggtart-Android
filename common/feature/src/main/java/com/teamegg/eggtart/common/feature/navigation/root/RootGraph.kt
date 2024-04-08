package com.teamegg.eggtart.common.feature.navigation.root

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.teamegg.eggtart.features.home.HomeScreen

/**
 *  Created by wonjin on 2024/04/04
 **/

@Composable
fun RootGraph(navHostController: NavHostController, startDestination: String, paddingValues: PaddingValues = PaddingValues()) {
    NavHost(modifier = Modifier.padding(paddingValues), navController = navHostController, startDestination = startDestination, route = Graph.ROOT) {
        composable(Graph.HOME) {
            HomeScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
}
