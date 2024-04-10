package com.teamegg.eggtart.features.write_goal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeAnimationTarget
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.teamegg.eggtart.common.feature.components.EggtartButton
import com.teamegg.eggtart.common.feature.components.EggtartButtonSize
import com.teamegg.eggtart.common.feature.components.EggtartButtonStyle
import com.teamegg.eggtart.common.feature.components.EggtartSelectionBox
import com.teamegg.eggtart.common.feature.components.EggtartTextField
import com.teamegg.eggtart.common.feature.types.StringResource
import com.teamegg.eggtart.common.util.Logger
import com.teamegg.eggtart.features.write_goal.components.SelectColorBottomSheet
import com.teamegg.eggtart.features.write_goal.components.WriteGoalAppBar
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

/**
 * Created by 노원진 on 2024.03.31
 */

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WriteGoalScreen(navHostController: NavHostController, viewModel: WriteGoalViewModel = hiltViewModel()) {
    val viewModelState = viewModel.collectAsState().value
    val focusManager = LocalFocusManager.current

    viewModel.intentSetImeBottom(WindowInsets.imeAnimationTarget.getBottom(LocalDensity.current))

    Scaffold(
        topBar = {
            WriteGoalAppBar(navHostController = navHostController)
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ) {
                viewModel.intentSetImeBottom(0)
            }) {
            Column(modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 28.dp)) {
                Text(text = stringResource(id = StringResource.com_goal), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45f))

                Spacer(modifier = Modifier.height(8.dp))

                EggtartTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onKeyEvent {
                            viewModelState.imeBottom != 0
                        },
                    keyboardActions = KeyboardActions {
                        Logger.d("keyboardActions")
                    },
                    placeHolder = stringResource(id = StringResource.enter_goal_hint),
                    value = viewModelState.goalString,
                    onValueChanged = { viewModel.intentSetGoalString(it) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = stringResource(id = StringResource.select_color), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45f))

                Spacer(modifier = Modifier.height(8.dp))

                EggtartSelectionBox(
                    placeHolder = stringResource(id = StringResource.select_color_hint),
                    onClick = {
                        viewModel.intentShowBottomSheet(true)
                    },
                    leading = {
                        if (viewModelState.goalColor != null) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(viewModelState.goalColor.color)
                            )
                        }
                    },
                    value = if (viewModelState.goalColor == null) "" else stringResource(id = viewModelState.goalColor.colorNameId)
                )

                Spacer(modifier = Modifier.weight(1f))

                EggtartButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    buttonSize = EggtartButtonSize.LARGE,
                    buttonStyle = EggtartButtonStyle.PRIMARY,
                    contentString = stringResource(id = StringResource.com_save),
                    onClick = {
                        /*TODO*/
                    })
            }
        }

        if (viewModelState.isShowBottomSheet)
            SelectColorBottomSheet()
    }

    viewModel.collectSideEffect {
        focusManager.clearFocus()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewWriteGoalScreen() {
    com.teamegg.eggtart.common.feature.theme.EggtartTheme {
        WriteGoalScreen(rememberNavController())
    }
}