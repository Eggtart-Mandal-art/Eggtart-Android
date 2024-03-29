package com.eggtart.eggtart.features.main.screens.mandalart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eggtart.eggtart.R
import org.orbitmvi.orbit.compose.collectAsState

/**
 * Created by 노원진 on 2024.03.24
 */

@Composable
fun MandalartScreen(viewModel: MandalartViewModel = hiltViewModel()) {
    remember { viewModel.getMandalartCells() }

    val viewModelState = viewModel.collectAsState().value

    val rootCell = viewModelState.mandalartCellList.getOrNull(4)

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
            items(9) { index ->
                val cell = viewModelState.mandalartCellList.getOrNull(index)

                if (cell?.id != rootCell?.id) {
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                if (rootCell?.color == null)
                                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.04f)
                                else if (cell?.color == null)
                                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.08f)
                                else
                                    Color(cell.color!!)
                            )
                            .clickable(
                                enabled = rootCell?.color != null
                            ) {
                                if (cell == null) {
                                    //TODO 생성페이지 이동
                                } else {
                                    //TODO 상세페이지 이동
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (rootCell?.color != null && cell?.color == null) {
                            Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "")
                        } else if (cell?.color != null) {
                            Text(text = cell.goal.toString())
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (rootCell?.color == null) Color.Black.copy(alpha = 0.1f) else Color(rootCell.color!!))
                            .clickable {
                                if (rootCell?.color == null) {
                                    // TODO 생성 페이지
                                } else {
                                    // TODO 상세 페이지
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .wrapContentSize(),
                            text = if (rootCell?.goal == null) stringResource(id = R.string.enter_final_goal_first) else rootCell.goal.toString(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45f)
                        )
                    }
                }
            }
        }
    }
}