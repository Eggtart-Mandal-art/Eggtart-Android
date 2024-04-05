package com.eggtart.eggtart.features.home.mandalart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.eggtart.eggtart.domain.util.Log
import com.eggtart.eggtart.features.home.mandalart.components.MandalartAppBar
import com.eggtart.eggtart.features.home.mandalart.components.MandalartItem
import org.orbitmvi.orbit.compose.collectAsState

/**
 * Created by 노원진 on 2024.03.24
 */

@Composable
fun MandalartScreen(navHostController: NavHostController, homePaddingValues: PaddingValues = PaddingValues(), viewModel: MandalartViewModel = hiltViewModel()) {
    val viewModelState = viewModel.collectAsState().value

    Scaffold(
        modifier = Modifier.padding(homePaddingValues),
        topBar = {
            MandalartAppBar()
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxWidth(1f),
                    userScrollEnabled = false,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val rootCell = viewModelState.mandalartCellList.getOrNull(4)

                    items(viewModelState.mandalartCellList) { cell ->
                        MandalartItem(navHostController = navHostController, rootCellData = rootCell, cellData = cell)
                    }
                }
            }
        }
    }
}