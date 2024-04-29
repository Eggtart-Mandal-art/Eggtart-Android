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
import com.teamegg.eggtart.common.feature.theme.ColorGray50
import com.teamegg.eggtart.common.feature.types.DrawableResource
import com.teamegg.eggtart.common.feature.types.StringResource
import com.teamegg.eggtart.domain.mandalart.model.ResCellModel

/**
 *  Created by wonjin on 2024/04/05
 **/

@Composable
fun MandalartItem(navigateWriteGoal: (ResCellModel) -> Unit, cellData: ResCellModel?, index: Int) {
    val isRootCell = index == 4

    if (cellData == null) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(ColorGray50)
        )
    } else {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (cellData.color == null) {
                        ColorGray50
                    } else {
                        Color(android.graphics.Color.parseColor("#${cellData.color}"))
                    }
                )
                .clickable {
                    navigateWriteGoal(cellData)
                },
            contentAlignment = Alignment.Center
        ) {
            if (isRootCell || cellData.goal.isNullOrEmpty().not()) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .wrapContentSize(),
                    text = if (cellData.goal.isNullOrEmpty()) stringResource(id = StringResource.enter_final_goal_first) else cellData.goal.toString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            } else {
                Icon(painter = painterResource(id = DrawableResource.ic_add), contentDescription = "", tint = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}