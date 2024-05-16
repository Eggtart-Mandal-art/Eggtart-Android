package com.teamegg.eggtart.features.home.mandalart.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamegg.eggtart.common.feature.components.EggtartButtonSize
import com.teamegg.eggtart.common.feature.components.EggtartIconButton
import com.teamegg.eggtart.common.feature.types.DrawableResource
import com.teamegg.eggtart.common.feature.types.StringResource
import com.teamegg.eggtart.features.home.mandalart.MandalartViewModel
import org.orbitmvi.orbit.compose.collectAsState

/**
 *  Created by wonjin on 2024/04/05
 **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MandalartAppBar(viewModel: MandalartViewModel = hiltViewModel()) {
    AnimatedContent(targetState = viewModel.collectAsState().value.childCellList, label = "") { childCells ->
        TopAppBar(
            modifier = Modifier.heightIn(max = 56.dp),
            title = {
                Box(modifier = Modifier.fillMaxHeight()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = if (childCells.getOrNull(4) != null) {
                            childCells[4].goal ?: ""
                        } else {
                            stringResource(id = StringResource.app_name)
                        },
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            navigationIcon = {
                if (viewModel.collectAsState().value.childCellList.isNotEmpty())
                    Box(modifier = Modifier.fillMaxHeight()) {
                        EggtartIconButton(
                            modifier = Modifier.align(Alignment.Center),
                            onClick = {
                                viewModel.intentClearChildCellList()
                            }, buttonSize = EggtartButtonSize.MEDIUM
                        ) {
                            Icon(painter = painterResource(id = DrawableResource.ic_arrow_back), contentDescription = null)
                        }
                    }
            }
        )
    }
}
