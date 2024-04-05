package com.eggtart.eggtart.features.home.mandalart.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eggtart.eggtart.R

/**
 *  Created by wonjin on 2024/04/05
 **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MandalartAppBar() {
    TopAppBar(
        modifier = Modifier.height(56.dp), title = {
            Text(modifier = Modifier.padding(vertical = 16.dp), text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.titleMedium)
        }
    )
}
