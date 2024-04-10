package com.teamegg.eggtart.common.feature.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamegg.eggtart.common.feature.theme.EggtartTheme

/**
 * Created by 노원진 on 2024.03.31
 */


@Composable
fun EggtartButton(
    modifier: Modifier = Modifier,
    contentString: String,
    buttonSize: EggtartButtonSize = EggtartButtonSize.LARGE,
    buttonStyle: EggtartButtonStyle = EggtartButtonStyle.PRIMARY,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = when (buttonStyle) {
            EggtartButtonStyle.PRIMARY -> ButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = Color.Black.copy(alpha = 0.1f),
                disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
            )

            EggtartButtonStyle.SECONDARY -> ButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                disabledContainerColor = Color.Black.copy(alpha = 0.1f),
                disabledContentColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.7f)
            )

            EggtartButtonStyle.TERTIARY -> ButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary,
                disabledContainerColor = Color.Black.copy(alpha = 0.1f),
                disabledContentColor = Color.Black.copy(alpha = 0.25f)
            )
        },
        shape = RoundedCornerShape(
            when (buttonSize) {
                EggtartButtonSize.LARGE -> 12.dp
                EggtartButtonSize.MEDIUM -> 10.dp
                EggtartButtonSize.SMALL -> 8.dp
            }
        ),
        contentPadding = when (buttonSize) {
            EggtartButtonSize.LARGE -> PaddingValues(vertical = 16.dp, horizontal = 20.dp)
            EggtartButtonSize.MEDIUM -> PaddingValues(vertical = 12.dp, horizontal = 16.dp)
            EggtartButtonSize.SMALL -> PaddingValues(vertical = 8.dp, horizontal = 12.dp)
        },
        content = {
            when (buttonSize) {
                EggtartButtonSize.LARGE -> Text(text = contentString, style = MaterialTheme.typography.labelLarge)
                EggtartButtonSize.MEDIUM -> Text(text = contentString, style = MaterialTheme.typography.labelMedium)
                EggtartButtonSize.SMALL -> Text(text = contentString, style = MaterialTheme.typography.labelSmall)
            }
        }
    )
}

enum class EggtartButtonSize {
    LARGE, MEDIUM, SMALL
}

enum class EggtartButtonStyle {
    PRIMARY, SECONDARY, TERTIARY
}

@Preview(showBackground = true)
@Composable
private fun ButtonPrimaryPreview() {
    EggtartTheme {
        Column {
            EggtartButton(
                contentString = "largeButton",
                buttonSize = EggtartButtonSize.LARGE,
                buttonStyle = EggtartButtonStyle.PRIMARY,
                onClick = { })
            EggtartButton(
                contentString = "mediumButton",
                buttonSize = EggtartButtonSize.MEDIUM,
                buttonStyle = EggtartButtonStyle.PRIMARY,
                onClick = { })
            EggtartButton(
                contentString = "smallButton",
                buttonSize = EggtartButtonSize.SMALL,
                buttonStyle = EggtartButtonStyle.PRIMARY,
                onClick = { })

            EggtartButton(
                contentString = "largeButton",
                buttonSize = EggtartButtonSize.LARGE,
                buttonStyle = EggtartButtonStyle.PRIMARY,
                enabled = false,
                onClick = { })
            EggtartButton(
                contentString = "mediumButton",
                buttonSize = EggtartButtonSize.MEDIUM,
                buttonStyle = EggtartButtonStyle.PRIMARY,
                enabled = false,
                onClick = { })
            EggtartButton(
                contentString = "smallButton",
                buttonSize = EggtartButtonSize.SMALL,
                buttonStyle = EggtartButtonStyle.PRIMARY,
                enabled = false,
                onClick = { })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonSecondaryPreview() {
    EggtartTheme {
        Column {
            EggtartButton(
                contentString = "largeButton",
                buttonSize = EggtartButtonSize.LARGE,
                buttonStyle = EggtartButtonStyle.SECONDARY,
                onClick = { })
            EggtartButton(
                contentString = "mediumButton",
                buttonSize = EggtartButtonSize.MEDIUM,
                buttonStyle = EggtartButtonStyle.SECONDARY,
                onClick = { })
            EggtartButton(
                contentString = "smallButton",
                buttonSize = EggtartButtonSize.SMALL,
                buttonStyle = EggtartButtonStyle.SECONDARY,
                onClick = { })
            EggtartButton(
                buttonSize = EggtartButtonSize.LARGE,
                contentString = "largeButton",
                buttonStyle = EggtartButtonStyle.SECONDARY,
                enabled = false,
                onClick = { })
            EggtartButton(
                contentString = "mediumButton",
                buttonSize = EggtartButtonSize.MEDIUM,
                buttonStyle = EggtartButtonStyle.SECONDARY,
                enabled = false,
                onClick = { })
            EggtartButton(
                contentString = "smallButton",
                buttonSize = EggtartButtonSize.SMALL,
                buttonStyle = EggtartButtonStyle.SECONDARY,
                enabled = false,
                onClick = { })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonTertiaryPreview() {
    EggtartTheme {
        Column {
            EggtartButton(
                buttonSize = EggtartButtonSize.LARGE,
                buttonStyle = EggtartButtonStyle.TERTIARY,
                contentString = "largeButton",
                onClick = { })
            EggtartButton(
                buttonSize = EggtartButtonSize.MEDIUM,
                contentString = "mediumButton",
                buttonStyle = EggtartButtonStyle.TERTIARY,
                onClick = { })
            EggtartButton(
                contentString = "smallButton",
                buttonSize = EggtartButtonSize.SMALL,
                buttonStyle = EggtartButtonStyle.TERTIARY,
                onClick = { })
            EggtartButton(
                buttonSize = EggtartButtonSize.LARGE,
                contentString = "largeButton",
                buttonStyle = EggtartButtonStyle.TERTIARY,
                enabled = false,
                onClick = { })
            EggtartButton(
                buttonSize = EggtartButtonSize.MEDIUM,
                contentString = "mediumButton",
                buttonStyle = EggtartButtonStyle.TERTIARY,
                enabled = false,
                onClick = { })
            EggtartButton(
                contentString = "smallButton",
                buttonSize = EggtartButtonSize.SMALL,
                buttonStyle = EggtartButtonStyle.TERTIARY,
                enabled = false,
                onClick = { })
        }
    }
}