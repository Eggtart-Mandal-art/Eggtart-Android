package com.teamegg.eggtart.features.write_goal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeAnimationTarget
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.teamegg.eggtart.common.feature.components.EggtartButton
import com.teamegg.eggtart.common.feature.components.EggtartButtonSize
import com.teamegg.eggtart.common.feature.components.EggtartButtonStyle
import com.teamegg.eggtart.common.feature.components.EggtartIconButton
import com.teamegg.eggtart.common.feature.components.EggtartSelectionBox
import com.teamegg.eggtart.common.feature.components.EggtartTextField
import com.teamegg.eggtart.common.feature.types.DrawableResource
import com.teamegg.eggtart.common.feature.types.StringResource
import com.teamegg.eggtart.common.util.Logger
import com.teamegg.eggtart.domain.mandalart.model.CellModel
import com.teamegg.eggtart.features.write_goal.components.SelectColorBottomSheet
import com.teamegg.eggtart.features.write_goal.components.WriteGoalAppBar
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

/**
 * Created by 노원진 on 2024.03.31
 */

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WriteGoalScreen(navHostController: NavHostController, cellModel: CellModel, viewModel: WriteGoalViewModel = hiltViewModel()) {
    val viewModelState = viewModel.collectAsState().value
    val focusManager = LocalFocusManager.current
    val todoFocusRequesters = remember { mutableStateMapOf<Int, FocusRequester>() }

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
            Column {
                LazyColumn(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    item {
                        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                modifier = Modifier.padding(vertical = 4.dp),
                                text = stringResource(id = StringResource.com_goal),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )

                            EggtartTextField(
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
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

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                modifier = Modifier.padding(vertical = 4.dp),
                                text = stringResource(id = StringResource.select_color),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )

                            EggtartSelectionBox(
                                modifier = Modifier.padding(vertical = 4.dp),
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

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                modifier = Modifier.padding(vertical = 4.dp),
                                text = stringResource(id = StringResource.com_todo),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }

                    itemsIndexed(viewModelState.todoList, key = { index, item -> index }) { index, todo ->
                        val focusRequester = FocusRequester()
                        todoFocusRequesters[index] = focusRequester

                        Row(
                            modifier = Modifier.padding(start = 16.dp, end = 6.dp, top = 4.dp, bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            EggtartTextField(modifier = Modifier
                                .weight(1f)
                                .focusRequester(focusRequester), value = todo, onValueChanged = {
                                viewModel.intentSetTodoString(index, it)
                            })

                            EggtartIconButton(
                                onClick = { viewModel.intentRemoveTodo(index) },
                                buttonSize = EggtartButtonSize.MEDIUM,
                                enabled = todo.isNotEmpty()
                            ) {
                                Icon(painter = painterResource(id = DrawableResource.ic_delete), contentDescription = "")
                            }
                        }
                    }

                    item {
                        TextButton(
                            modifier = Modifier.padding(top = 4.dp),
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = MaterialTheme.colorScheme.secondary
                            ),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                            shape = RoundedCornerShape(10.dp),
                            onClick = {
                                viewModel.intentAddTodo()
                            }) {
                            Icon(painter = painterResource(id = DrawableResource.ic_add), contentDescription = "")
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = stringResource(id = StringResource.add_todo), style = MaterialTheme.typography.labelMedium)
                        }
                    }
                }

                EggtartButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 8.dp,
                            bottom = 24.dp,
                            start = 20.dp,
                            end = 20.dp
                        ),
                    buttonSize = EggtartButtonSize.LARGE,
                    buttonStyle = EggtartButtonStyle.PRIMARY,
                    contentString = stringResource(id = StringResource.com_save),
                    enabled = viewModelState.goalColor != null && viewModelState.goalString.isNotEmpty(),
                    onClick = {
                        /*TODO*/
                    }
                )
            }
        }

        if (viewModelState.isShowBottomSheet)
            SelectColorBottomSheet()
    }

    viewModel.collectSideEffect {
        when (it) {
            is WriteGoalSideEffect.HideKeyboard -> {
                focusManager.clearFocus()
                viewModel.intentRemoveEmptyTodo()
            }

            is WriteGoalSideEffect.RequestFocus -> todoFocusRequesters[viewModelState.todoList.lastIndex]?.requestFocus()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewWriteGoalScreen() {
    com.teamegg.eggtart.common.feature.theme.EggtartTheme {
        WriteGoalScreen(cellModel = CellModel(0, 0, 0), navHostController = rememberNavController())
    }
}