package com.teamegg.eggtart.features.write_goal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamegg.eggtart.common.feature.util.Constants.GOAL_COLORS
import com.teamegg.eggtart.features.write_goal.WriteGoalViewModel

/**
 *  Created by wonjin on 2024/04/05
 **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectColorBottomSheet(bottomSheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true), viewModel: WriteGoalViewModel = hiltViewModel()) {
    ModalBottomSheet(
        sheetState = bottomSheetState,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        dragHandle = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 48.dp, height = 4.dp)
                        .clip(shape = RoundedCornerShape(100.dp))
                        .background(MaterialTheme.colorScheme.onBackground.copy(0.25f))
                )
            }
        },
        onDismissRequest = { viewModel.intentShowBottomSheet(false) },
        windowInsets = BottomSheetDefaults.windowInsets.only(WindowInsetsSides.Bottom),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        GOAL_COLORS.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.intentSetGoalColorModel(it)
                    }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(shape = RoundedCornerShape(4.dp))
                        .background(it.color)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = stringResource(id = it.colorNameId), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}