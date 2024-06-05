package com.baker.eggtart.common.feature.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baker.eggtart.common.feature.theme.EggtartTheme

/**
 * Created by 노원진 on 2024.03.31
 */

@Composable
fun EggtartTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolder: String = "",
    readOnly: Boolean = false,
    onValueChanged: (String) -> Unit,
    enabled: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    minLines: Int = 1,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
) {
    OutlinedTextField(
        modifier = modifier,
        enabled = enabled,
        value = value,
        readOnly = readOnly,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        onValueChange = onValueChanged,
        placeholder = {
            Text(text = placeHolder, style = MaterialTheme.typography.bodyMedium)
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        minLines = minLines,
        maxLines = maxLines,
        singleLine = singleLine,
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = MaterialTheme.colorScheme.onBackground.copy(0.05f),
            focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(0.1f),
            disabledPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(0.25f),
            focusedPlaceholderColor = Color.Transparent,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(0.45f),
        ),
        shape = RoundedCornerShape(8.dp),
    )
}


@Preview(showBackground = true)
@Composable
private fun PreviewEggtartTextField() {
    EggtartTheme {
        Column {
            EggtartTextField(placeHolder = "플레이스 홀더(enabled)", value = "", onValueChanged = {}, enabled = true)
            EggtartTextField(placeHolder = "플레이스 홀더(enabled)", value = "입력된 값(enabled)", onValueChanged = {}, enabled = true)
            EggtartTextField(placeHolder = "플레이스 홀더(disabled)", value = "", onValueChanged = {}, enabled = false)
            EggtartTextField(placeHolder = "플레이스 홀더(disabled)", value = "입력된 값(disabled)", onValueChanged = {}, enabled = false)
        }
    }
}