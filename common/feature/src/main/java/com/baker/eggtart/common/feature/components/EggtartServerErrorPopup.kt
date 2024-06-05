package com.baker.eggtart.common.feature.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.baker.eggtart.common.feature.types.StringResource
import com.baker.eggtart.common.util.ServerResult

/**
 *    Created by 노원진 on 2024/05/02
 **/

@Composable
fun EggtartServerErrorPopup(serverErrorDialogData: ServerErrorDialogData) {
    if (serverErrorDialogData.serverResult is ServerResult.Failure) {
        val message = when (serverErrorDialogData.serverResult.error?.status) {
            40102 -> {
                stringResource(id = StringResource.popup_expired_token_error_content)
            }

            40103 -> {
                stringResource(id = StringResource.popup_invalid_token_error_content)
            }

            else -> {
                stringResource(id = StringResource.popup_network_error_content)
            }
        }
        EggtartPopup(
            dialogData = DialogData(
                content = message,
                confirm = stringResource(id = StringResource.com_confirm),
                dismiss = null,
                onDismiss = serverErrorDialogData.onDismiss,
                onConfirm = {
                    serverErrorDialogData.onDismiss()

                    if (serverErrorDialogData.serverResult.error?.status == 40102 || serverErrorDialogData.serverResult.error?.status == 40103) {
                        serverErrorDialogData.onClearLoginData()
                    } else {
                        serverErrorDialogData.onConfirm()
                    }
                }
            )
        )
    } else if (serverErrorDialogData.serverResult is ServerResult.Exception) {
        EggtartPopup(
            dialogData = DialogData(
                content = stringResource(id = StringResource.popup_unknown_error_content),
                confirm = stringResource(id = StringResource.com_confirm),
                dismiss = null,
                onDismiss = serverErrorDialogData.onDismiss,
                onConfirm = serverErrorDialogData.onConfirm
            )
        )
    }
}

data class ServerErrorDialogData(
    val serverResult: ServerResult<*>,
    val onDismiss: () -> Unit,
    val onConfirm: () -> Unit,
    val onClearLoginData: () -> Unit
)