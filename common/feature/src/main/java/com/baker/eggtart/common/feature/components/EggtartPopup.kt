package com.baker.eggtart.common.feature.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.baker.eggtart.common.feature.theme.EggtartTheme

/**
 *    Created by 노원진 on 2024/04/29
 **/

@Composable
fun EggtartPopup(
    dialogData: DialogData
) {
    Dialog(
        onDismissRequest = dialogData.onDismiss,
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .clickable(interactionSource = remember {
                    MutableInteractionSource()
                }, indication = null) {
                    if (dialogData.dismiss != null) {
                        dialogData.onDismiss()
                    }
                }, contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .widthIn(max = 312.dp)
                        .clickable(enabled = false) {},
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 16.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(
                            top = 20.dp,
                            start = 20.dp,
                            end = 20.dp,
                            bottom = 16.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Title
                        if (!dialogData.title.isNullOrEmpty()) {
                            Text(
                                modifier = Modifier.padding(vertical = 4.dp),
                                text = dialogData.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        // Content
                        Text(
                            text = dialogData.content,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )

                        // Buttons
                        Row {
                            if (dialogData.dismiss != null) {
                                EggtartButton(
                                    modifier = Modifier.weight(1f),
                                    contentString = dialogData.dismiss,
                                    buttonSize = EggtartButtonSize.MEDIUM,
                                    buttonStyle = EggtartButtonStyle.TERTIARY,
                                    onClick = dialogData.onDismiss
                                )
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            EggtartButton(
                                modifier = Modifier.weight(1f),
                                contentString = dialogData.confirm,
                                buttonSize = EggtartButtonSize.MEDIUM,
                                onClick = {
                                    dialogData.onDismiss()
                                    dialogData.onConfirm()
                                }
                            )
                        }
                    }
                }
            }
        },
        properties = DialogProperties(
            dismissOnBackPress = dialogData.dismiss != null,
            dismissOnClickOutside = dialogData.dismiss != null
        )
    )
}

data class DialogData(
    val title: String? = null,
    val content: String,
    val confirm: String,
    val dismiss: String? = null,
    val onDismiss: () -> Unit,
    val onConfirm: () -> Unit
)

@Composable
@Preview(
    showSystemUi = true,
    device = Devices.DEFAULT
)
private fun EggtartPopupPreview() {
    EggtartTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            EggtartPopup(
                dialogData = DialogData(
                    title = "삭제하기",
                    content = "작성한 목표를 삭제하시겠습니까?",
                    confirm = "예",
                    dismiss = "아니오",
                    onDismiss = {},
                    onConfirm = {}
                ),
            )
        }
    }
}