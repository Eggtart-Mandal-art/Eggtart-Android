package com.teamegg.eggtart.features.write_goal.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.teamegg.eggtart.common.feature.R

/**
 *  Created by wonjin on 2024/04/05
 **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteGoalAppBar(navHostController: NavHostController) {
    CenterAlignedTopAppBar(
        modifier = Modifier.heightIn(max = 56.dp),
        title = {
            Text(
                text = stringResource(id = R.string.write_goal_title),
                style = MaterialTheme.typography.titleMedium,
            )
        },
        navigationIcon = {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "")
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