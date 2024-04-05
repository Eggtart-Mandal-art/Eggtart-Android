package com.eggtart.eggtart.features.goal.write

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imeAnimationTarget
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.eggtart.eggtart.R
import com.eggtart.eggtart.common.constants.Constants
import com.eggtart.eggtart.common.ui.components.EggtartButton
import com.eggtart.eggtart.common.ui.components.EggtartButtonSize
import com.eggtart.eggtart.common.ui.components.EggtartButtonStyle
import com.eggtart.eggtart.common.ui.components.EggtartSelectionBox
import com.eggtart.eggtart.common.ui.components.EggtartTextField
import com.eggtart.eggtart.common.ui.theme.EggtartTheme
import com.eggtart.eggtart.domain.util.Log
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

/**
 * Created by 노원진 on 2024.03.31
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun WriteGoalScreen(navHostController: NavHostController, viewModel: WriteGoalViewModel = hiltViewModel()) {
    val viewModelState = viewModel.collectAsState().value
    val focusManager = LocalFocusManager.current

    viewModel.intentSetImeBottom(WindowInsets.imeAnimationTarget.getBottom(LocalDensity.current))

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.heightIn(max = 56.dp),
                title = {
                    Text(
                        text = stringResource(id = R.string.write_goal_title),
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "")
                    }
                },
//                actions = {
//                    IconButton(onClick = { navHostController.popBackStack() }) {
//                        Icon(painter = painterResource(id = R.drawable.ic_close), contentDescription = "")
//                    }
//                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    titleContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    actionIconContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                )
            )
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
                Text(text = stringResource(id = R.string.com_goal), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45f))

                Spacer(modifier = Modifier.height(8.dp))

                EggtartTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onKeyEvent {
                            viewModelState.imeBottom != 0
                        },
                    keyboardActions = KeyboardActions {
                        Log.d("keyboardActions")
                    },
                    placeHolder = stringResource(id = R.string.enter_goal_hint),
                    value = viewModelState.goalString,
                    onValueChanged = { viewModel.intentSetGoalString(it) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = stringResource(id = R.string.select_color), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45f))

                Spacer(modifier = Modifier.height(8.dp))

                EggtartSelectionBox(
                    placeHolder = stringResource(id = R.string.select_color_hint),
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
                    contentString = stringResource(id = R.string.com_save),
                    onClick = {
                        /*TODO*/
                    })
            }
        }
        val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        if (viewModelState.isShowBottomSheet)
            ModalBottomSheet(
                sheetState = bottomSheetState,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                dragHandle = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(width = 48.dp, height = 4.dp)
                                .clip(shape = RoundedCornerShape(100.dp))
                                .background(MaterialTheme.colorScheme.onBackground.copy(0.25f))
                        )
                    }
                },
                onDismissRequest = { viewModel.intentShowBottomSheet(false) },
                windowInsets = BottomSheetDefaults.windowInsets.only(WindowInsetsSides.Bottom),
                containerColor = MaterialTheme.colorScheme.background
            ) {
                Constants.GOAL_COLORS.forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.intentSetGoalColorModel(it)
                            }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(shape = RoundedCornerShape(4.dp))
                                .background(it.color)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = stringResource(id = it.colorNameId), style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
    }

    viewModel.collectSideEffect {
        focusManager.clearFocus()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewWriteGoalScreen() {
    EggtartTheme {
        WriteGoalScreen(rememberNavController())
    }
}