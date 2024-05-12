package com.teamegg.eggtart.features.home.mandalart.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamegg.eggtart.common.feature.theme.ColorGray50
import com.teamegg.eggtart.common.feature.types.DrawableResource
import com.teamegg.eggtart.common.feature.types.StringResource
import com.teamegg.eggtart.domain.mandalart.model.CellModel
import com.teamegg.eggtart.features.home.mandalart.MandalartViewModel

/**
 *  Created by wonjin on 2024/04/05
 **/

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MandalartItem(
    navigateWriteGoal: (CellModel) -> Unit,
    cellData: CellModel?,
    isChild: Boolean,
    index: Int,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    navSharedTransitionScope: SharedTransitionScope,
    navAnimatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: MandalartViewModel = hiltViewModel()
) {
    val isRootCell = index == 4

    if (cellData == null) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(ColorGray50)
        )
    } else {
        with(sharedTransitionScope) {
            Box(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = cellData.id),
                        animatedVisibilityScope = animatedContentScope
                    )
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
                        if (isChild || isRootCell || cellData.goal.isNullOrEmpty()) {
                            navigateWriteGoal(cellData)
                        } else {
                            viewModel.intentGetChildCellList(cellData.id)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                if (isRootCell || cellData.goal.isNullOrEmpty().not()) {
                    with(navSharedTransitionScope) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .wrapContentSize()
                                .sharedElement(animatedVisibilityScope = navAnimatedVisibilityScope, state = rememberSharedContentState(key = "goal-${cellData.id}")),
                            text = if (cellData.goal.isNullOrEmpty()) stringResource(id = StringResource.enter_final_goal_first) else cellData.goal.toString(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                } else {
                    Icon(painter = painterResource(id = DrawableResource.ic_add), contentDescription = "", tint = MaterialTheme.colorScheme.secondary)
                }
            }
        }
    }
}