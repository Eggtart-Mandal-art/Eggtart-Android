package com.teamegg.eggtart.features.home.mandalart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamegg.eggtart.features.home.mandalart.components.MandalartAppBar
import com.teamegg.eggtart.features.home.mandalart.components.MandalartItem
import org.orbitmvi.orbit.compose.collectAsState

/**
 * Created by 노원진 on 2024.03.24
 */

@Composable
fun MandalartScreen(navigateWriteGoal: (Int) -> Unit, homePaddingValues: PaddingValues = PaddingValues(), viewModel: MandalartViewModel = hiltViewModel()) {
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

                    items(9) { index ->
                        MandalartItem(navigateWriteGoal = navigateWriteGoal, rootCellData = rootCell, cellData = viewModelState.mandalartCellList.getOrNull(index), index = index)
                    }
                }
            }
        }
    }
}