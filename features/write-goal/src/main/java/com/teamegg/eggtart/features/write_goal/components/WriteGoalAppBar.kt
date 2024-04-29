package com.teamegg.eggtart.features.write_goal.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamegg.eggtart.common.feature.components.EggtartButtonSize
import com.teamegg.eggtart.common.feature.components.EggtartIconButton
import com.teamegg.eggtart.common.feature.types.DrawableResource
import com.teamegg.eggtart.common.feature.types.StringResource
import com.teamegg.eggtart.domain.mandalart.model.ResCellModel
import com.teamegg.eggtart.domain.mandalart.model.ResCellTodosModel
import com.teamegg.eggtart.features.write_goal.WriteGoalViewModel

/**
 *  Created by wonjin on 2024/04/05
 **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteGoalAppBar(navigateHome: (ResCellTodosModel?) -> Unit, cellModel: ResCellModel, viewModel: WriteGoalViewModel = hiltViewModel()) {
    CenterAlignedTopAppBar(
        modifier = Modifier.heightIn(max = 56.dp),
        title = {
            Text(
                text = if (cellModel.goal.isNullOrEmpty()) stringResource(id = StringResource.write_goal_title) else cellModel.goal!!,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            EggtartIconButton(onClick = { navigateHome(null) }, buttonSize = EggtartButtonSize.MEDIUM) {
                Icon(painter = painterResource(id = DrawableResource.ic_arrow_back), contentDescription = "")
            }
        },
        actions = {
            if (!cellModel.goal.isNullOrEmpty()) {
                EggtartIconButton(onClick = {
                    viewModel.intentDeleteCell(cellModel)
                }, buttonSize = EggtartButtonSize.MEDIUM) {
                    Icon(painter = painterResource(id = DrawableResource.ic_delete), contentDescription = "")
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            titleContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            actionIconContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
        )
    )
}