package com.baker.eggtart.common.feature.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 *  Created by wonjin on 2024/04/05
 **/

@Composable
fun EggtartSnackbar(hostState: SnackbarHostState, modifier: Modifier = Modifier) {
    SnackbarHost(hostState = hostState, modifier = modifier) { snackbarData ->
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f))
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(text = snackbarData.visuals.message, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.background)
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun EggtartToastPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f))
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(text = "showToastMessage", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.background)
        }
    }
}