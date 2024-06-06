package com.baker.eggtart.features.home.mandalart

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.baker.eggtart.common.feature.components.EggtartServerErrorPopup
import com.baker.eggtart.common.feature.components.EggtartSnackbar
import com.baker.eggtart.common.feature.components.ServerErrorDialogData
import com.baker.eggtart.common.util.Logger
import com.baker.eggtart.domain.mandalart.model.CellModel
import com.baker.eggtart.domain.mandalart.model.CellTodosModel
import com.baker.eggtart.features.home.mandalart.components.MandalartAppBar
import com.baker.eggtart.features.home.mandalart.components.MandalartItem
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

/**
 * Created by 노원진 on 2024.03.24
 */

@Composable
fun MandalartScreen(
    navigateWriteGoal: (CellModel) -> Unit,
    homePaddingValues: PaddingValues = PaddingValues(),
    sheetIds: List<Long>,
    cellModel: CellTodosModel?,
    viewModel: MandalartViewModel = hiltViewModel()
) {
    val viewModelState = viewModel.collectAsState().value
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (viewModelState.mandalartCellList.isEmpty()) {
            viewModel.intentGetMandalartCells(sheetIds)
        } else if (cellModel != null) {
            viewModel.intentUpdateCellModel(cellModel)
        }
    }

    Scaffold(
        modifier = Modifier.padding(homePaddingValues),
        topBar = {
            MandalartAppBar()
        },
        snackbarHost = {
            EggtartSnackbar(modifier = Modifier.padding(bottom = 24.dp), hostState = snackBarHostState)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxWidth(1f),
                userScrollEnabled = false,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(9) { index ->
                    MandalartItem(navigateWriteGoal = navigateWriteGoal, cellData = viewModelState.mandalartCellList.getOrNull(index), index = index)
                }
            }
        }

        if (viewModelState.serverErrorDialogData != null) {
            EggtartServerErrorPopup(viewModelState.serverErrorDialogData)
        }
    }

    viewModel.collectSideEffect {
        when (it) {
            is MandalartSideEffect.SnackBarRes -> {
                Logger.d("showSnackBar() ${context.getString(it.messageRes)}")
                snackBarHostState.showSnackbar(context.getString(it.messageRes))
            }

            is MandalartSideEffect.SnackBarString -> {
                snackBarHostState.showSnackbar(it.message)
            }

            is MandalartSideEffect.ServerErrorPopup -> {
                when (it.type) {
                    ServerCallType.GET_CELL_DATA -> {
                        viewModel.intentSetServerErrorData(
                            ServerErrorDialogData(
                                serverResult = it.serverResult,
                                onConfirm = {
                                    (context as? Activity)?.finish()
                                },
                                onDismiss = {
                                    viewModel.intentSetServerErrorData(null)
                                },
                                onClearLoginData = {
                                    viewModel.intentClearLoginData()
                                }
                            )
                        )
                    }
                }
            }
        }
    }
}