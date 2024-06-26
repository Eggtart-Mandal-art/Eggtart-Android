package com.baker.eggtart.common.feature.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baker.eggtart.common.feature.theme.EggtartTheme
import com.baker.eggtart.common.feature.types.DrawableResource

/**
 * Created by 노원진 on 2024.03.31
 */

@Composable
fun EggtartSelectionBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    placeHolder: String = "",
    value: String? = null,
    leading: @Composable (() -> Unit)? = null,
) {
    OutlinedButton(
        onClick = onClick, shape = RoundedCornerShape(8.dp),
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if (enabled)
                MaterialTheme.colorScheme.onBackground.copy(0.1f)
            else
                MaterialTheme.colorScheme.onBackground.copy(0.05f)
        )
    ) {
        if (value.isNullOrEmpty().not() && leading != null) {
            leading()
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            modifier = Modifier.weight(1f),
            text = if (value.isNullOrEmpty()) placeHolder else value,
            style = MaterialTheme.typography.bodyMedium,
            color = if (!enabled)
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
            else if (value.isNullOrEmpty()) {
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45f)
            } else {
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            }
        )
        Icon(painter = painterResource(id = DrawableResource.ic_expand_more), contentDescription = "", tint = MaterialTheme.colorScheme.secondary)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEggtartSelectionBox() {
    EggtartTheme {
        Column {
            EggtartSelectionBox(onClick = { /*TODO*/ }, enabled = true, placeHolder = "플레이스 홀더(enabled)")
            EggtartSelectionBox(onClick = { /*TODO*/ }, enabled = true, placeHolder = "플레이스 홀더(enabled)", value = "선택된 값(enabled)")
            EggtartSelectionBox(onClick = { /*TODO*/ }, enabled = false, placeHolder = "플레이스 홀더(disabled)")
            EggtartSelectionBox(onClick = { /*TODO*/ }, enabled = false, placeHolder = "플레이스 홀더(disabled)", value = "선택된 값(disabled)")
        }
    }
}