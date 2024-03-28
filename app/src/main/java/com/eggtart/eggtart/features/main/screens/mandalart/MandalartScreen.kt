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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState

/**
 * Created by 노원진 on 2024.03.24
 */

@Composable
fun MandalartScreen(viewModel: MandalartViewModel = hiltViewModel()) {
    val viewModelState = viewModel.collectAsState().value
    val sheet = viewModelState.mandalartSheetList.collectAsState(initial = listOf()).value.firstOrNull()

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
                if (index != 4) {
                    val cell = sheet?.depth1Cell?.children?.firstOrNull { it.order == index }

                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                if (sheet == null)
                                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.04f)
                                else if (cell == null)
                                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.08f)
                                else
                                    Color(cell.color)
                            )
                            .clickable(
                                enabled = sheet != null
                            ) {
                                if (cell == null) {
                                    //TODO 생성페이지 이동
                                } else {
                                    //TODO 상세페이지 이동
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (sheet != null && cell == null) {
                            Icon(imageVector = Icons.Outlined.Add, contentDescription = "추가하기")
                        } else if (cell != null) {
                            Text(text = "목표")
                        }
                    }
                } else {
                    val rootCell = sheet?.depth1Cell

                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (rootCell == null) Color.Black.copy(alpha = 0.1f) else Color(rootCell.color))
                            .clickable {
                                if (rootCell == null) {
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
                            text = if (rootCell == null) "먼저 최종 목표를 입력해주세요" else "목표",
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