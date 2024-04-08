package com.teamegg.eggtart.features.home.mandalart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.teamegg.eggtart.R
import com.teamegg.eggtart.domain.model.remote.ResponseMandalartCellModel
import com.teamegg.eggtart.navigation.home.HomeRoute

/**
 *  Created by wonjin on 2024/04/05
 **/

@Composable
fun MandalartItem(navHostController: NavHostController, rootCellData: ResponseMandalartCellModel?, cellData: ResponseMandalartCellModel) {
    val isRootCell = rootCellData?.id == cellData.id

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (isRootCell) {
                    if (cellData.color == null)
                        Color.Black.copy(alpha = 0.1f)
                    else
                        Color(cellData.color!!)
                } else {
                    if (rootCellData?.color == null)
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.04f)
                    else if (cellData.color == null)
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.08f)
                    else
                        Color(cellData.color!!)
                }
            )
            .clickable(enabled = isRootCell || rootCellData?.color != null) {
                if (cellData.color == null) {
                    navHostController.navigate(HomeRoute.WriteGoal.route)
                } else {
                    // TODO 상세 페이지
                }
            },
        contentAlignment = Alignment.Center
    ) {
        if (isRootCell || cellData.goal.isNullOrEmpty().not()) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .wrapContentSize(),
                text = if (cellData.goal.isNullOrEmpty()) stringResource(id = R.string.enter_final_goal_first) else cellData.goal.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45f)
            )
        } else if (rootCellData?.goal.isNullOrEmpty().not()) {
            Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "")
        }
    }
}