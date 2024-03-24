package com.eggtart.eggtart.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.eggtart.eggtart.R
import com.eggtart.eggtart.common.BaseSideEffect
import kotlinx.coroutines.Job

/**
 *  Created by wonjin on 2024/03/22
 **/

@Composable
fun BaseSideEffectPopup(sideEffectPopup: BaseSideEffect.ShowPopup, dismissRequest: () -> Job) {
    Dialog(onDismissRequest = { dismissRequest() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .widthIn(max = 600.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(24.dp)
        ) {
            Text(text = sideEffectPopup.message, style = TextStyle(color = MaterialTheme.colorScheme.onSurface))
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(onClick = {
                    dismissRequest()
                }) {
                    Text(text = LocalContext.current.resources.getString(R.string.com_cancel))
                }
                TextButton(onClick = {
                    dismissRequest()
                }) {
                    Text(text = LocalContext.current.resources.getString(R.string.com_confirm))
                }
            }
        }
    }
}