package com.teamegg.eggtart.features.write_goal

import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.teamegg.eggtart.common.feature.components.DialogData
import com.teamegg.eggtart.common.feature.components.ServerErrorDialogData
import com.teamegg.eggtart.common.feature.util.GoalColorModel
import com.teamegg.eggtart.common.util.ServerResult
import com.teamegg.eggtart.domain.mandalart.model.CellModel
import com.teamegg.eggtart.domain.mandalart.model.UpdateCellModel
import com.teamegg.eggtart.domain.mandalart.usecases.cell.DeleteMandalartCellUseCase
import com.teamegg.eggtart.domain.mandalart.usecases.cell.GetMandalartCellDetailUseCase
import com.teamegg.eggtart.domain.mandalart.usecases.cell.UpdateMandalartCellUseCase
import com.teamegg.eggtart.domain.user.usecase.SetLocalUserTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 * Created by 노원진 on 2024.03.31
 */

@HiltViewModel
class WriteGoalViewModel @Inject constructor(
    private val updateMandalartCellUseCase: UpdateMandalartCellUseCase,
    private val deleteMandalartCellUseCase: DeleteMandalartCellUseCase,
    private val getMandalartCellDetailUseCase: GetMandalartCellDetailUseCase,
    private val setLocalUserTokenUseCase: SetLocalUserTokenUseCase
) : ContainerHost<WriteGoalState, WriteGoalSideEffect>, ViewModel() {
    override val container: Container<WriteGoalState, WriteGoalSideEffect> = container(WriteGoalState())

    fun postUnSaveFinish() = intent {
        postSideEffect(WriteGoalSideEffect.PopupDialog(PopupType.WITHOUT_SAVE_FINISH))
    }

    fun postDeleteCell() = intent {
        postSideEffect(WriteGoalSideEffect.PopupDialog(PopupType.DELETE_CELL))
    }

    fun intentSetGoalString(value: String) = blockingIntent {
        reduce {
            state.copy(goalString = value)
        }
    }

    fun intentAddTodo() = intent {
        reduce {
            state.copy(todoList = state.todoList.toMutableList().apply {
                add("")
            })
        }

        delay(200)

        postSideEffect(WriteGoalSideEffect.RequestFocus)
    }

    fun intentRemoveTodo(index: Int) = intent {
        reduce {
            state.copy(todoList = state.todoList.toMutableList().apply {
                removeAt(index)
            })
        }
    }

    fun intentRemoveEmptyTodo() = intent {
        reduce {
            state.copy(
                todoList = state.todoList.toMutableList().apply {
                    removeAll { it.isEmpty() }
                }
            )
        }
    }

    fun intentSetTodoString(index: Int, value: String) = blockingIntent {
        reduce {
            state.copy(todoList = state.todoList.toMutableList().apply {
                this[index] = value
            })
        }
    }

    fun intentSetImeBottom(value: Int) = intent {
        if (value != state.imeBottom) {
            reduce {
                state.copy(imeBottom = value)
            }

            if (value == 0) {
                postSideEffect(sideEffect = WriteGoalSideEffect.HideKeyboard)
            }
        }
    }

    fun intentShowBottomSheet(value: Boolean) = intent {
        reduce {
            state.copy(isShowBottomSheet = value)
        }
    }

    fun intentSetGoalColorModel(value: GoalColorModel?) = intent {
        reduce {
            state.copy(goalColor = value, isShowBottomSheet = false)
        }
    }

    fun intentUpdateCell(cellModel: CellModel) = intent {
        reduce {
            state.copy(updateCellLoading = true)
        }

        val result = updateMandalartCellUseCase(
            cellId = cellModel.id,
            updateCellModel = UpdateCellModel(
                color = Integer.toHexString(state.goalColor?.color?.toArgb() ?: 0).uppercase(),
                goal = state.goalString,
                isCompleted = false,
                todos = state.todoList
            )
        )

        when (result) {
            is ServerResult.Success -> {
                postSideEffect(WriteGoalSideEffect.FinishResult(result.data))
            }

            else -> {
                postSideEffect(WriteGoalSideEffect.ServerErrorPopup(ServerCallType.UPDATE_CELL, result))
            }
        }

        reduce {
            state.copy(updateCellLoading = false)
        }
    }

    fun intentDeleteCell(cellModel: CellModel) = intent {
        reduce {
            state.copy(updateCellLoading = true)
        }

        when (val result = deleteMandalartCellUseCase(cellId = cellModel.id)) {
            is ServerResult.Success -> {
                postSideEffect(WriteGoalSideEffect.FinishResult(result.data))
            }

            else -> {
                postSideEffect(WriteGoalSideEffect.ServerErrorPopup(ServerCallType.DELETE_CELL, result))
            }
        }

        reduce {
            state.copy(updateCellLoading = false)
        }
    }

    fun intentGetMandalartCellDetail(cellModel: CellModel) = intent {
        reduce { state.copy(getTodosLoading = true) }

        when (val result = getMandalartCellDetailUseCase(cellId = cellModel.id)) {
            is ServerResult.Success -> {
                reduce {
                    state.copy(
                        getTodosLoading = false,
                        origTodos = result.data.todos.map { it.content },
                        todoList = result.data.todos.map { it.content }
                    )
                }
            }

            else -> {
                postSideEffect(WriteGoalSideEffect.ServerErrorPopup(ServerCallType.GET_CELL_DETAIL, result))
            }
        }
    }

    fun intentSetDialogData(dialogData: DialogData?) = intent {
        reduce { state.copy(dialogData = dialogData) }
    }

    fun intentSetServerErrorData(serverErrorDialogData: ServerErrorDialogData?) = intent {
        reduce { state.copy(serverErrorDialogData = serverErrorDialogData) }
    }

    fun intentClearLoginData() = intent {
        setLocalUserTokenUseCase(null)
    }
}