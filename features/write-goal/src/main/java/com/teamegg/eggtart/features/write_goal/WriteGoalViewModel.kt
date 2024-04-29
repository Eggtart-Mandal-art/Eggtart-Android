package com.teamegg.eggtart.features.write_goal

import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.teamegg.eggtart.common.feature.util.GoalColorModel
import com.teamegg.eggtart.common.util.Result
import com.teamegg.eggtart.domain.mandalart.model.ResCellModel
import com.teamegg.eggtart.domain.mandalart.model.UpdateCellModel
import com.teamegg.eggtart.domain.mandalart.usecases.cell.DeleteMandalartCellUseCase
import com.teamegg.eggtart.domain.mandalart.usecases.cell.GetMandalartCellDetailUseCase
import com.teamegg.eggtart.domain.mandalart.usecases.cell.UpdateMandalartCellUseCase
import com.teamegg.eggtart.domain.user.usecase.GetLocalUserTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
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
    private val getLocalUserTokenUseCase: GetLocalUserTokenUseCase,
    private val updateMandalartCellUseCase: UpdateMandalartCellUseCase,
    private val deleteMandalartCellUseCase: DeleteMandalartCellUseCase,
    private val getMandalartCellDetailUseCase: GetMandalartCellDetailUseCase,
) : ContainerHost<WriteGoalState, WriteGoalSideEffect>, ViewModel() {
    override val container: Container<WriteGoalState, WriteGoalSideEffect> = container(WriteGoalState())

    @OptIn(OrbitExperimental::class)
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

    @OptIn(OrbitExperimental::class)
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

    fun intentUpdateCell(cellModel: ResCellModel) = intent {
        reduce {
            state.copy(updateCellLoading = true)
        }

        val accessToken = getLocalUserTokenUseCase().firstOrNull()?.accessToken

        val result = updateMandalartCellUseCase(
            accessToken = accessToken ?: "",
            cellId = cellModel.id,
            updateCellModel = UpdateCellModel(
                color = Integer.toHexString(state.goalColor?.color?.toArgb() ?: 0).uppercase(),
                goal = state.goalString,
                isCompleted = false,
                todos = state.todoList
            )
        )

        when (result) {
            is Result.Success -> {
                postSideEffect(WriteGoalSideEffect.FinishResult(result.data))
            }

            is Result.Failure -> {

            }

            is Result.Exception -> {

            }
        }

        reduce {
            state.copy(updateCellLoading = false)
        }
    }

    fun intentDeleteCell(cellModel: ResCellModel) = intent {
        reduce {
            state.copy(updateCellLoading = true)
        }

        val accessToken = getLocalUserTokenUseCase().firstOrNull()?.accessToken

        val result = deleteMandalartCellUseCase(
            accessToken = accessToken ?: "",
            cellId = cellModel.id
        )

        when (result) {
            is Result.Success -> {
                postSideEffect(WriteGoalSideEffect.FinishResult(result.data))
            }

            is Result.Failure -> {

            }

            is Result.Exception -> {

            }
        }

        reduce {
            state.copy(updateCellLoading = false)
        }
    }

    fun intentGetMandalartCellDetail(cellModel: ResCellModel) = intent {
        reduce { state.copy(getTodosLoading = true) }

        val accessToken = getLocalUserTokenUseCase().firstOrNull()?.accessToken ?: ""

        when (val result = getMandalartCellDetailUseCase(accessToken = accessToken, cellId = cellModel.id)) {
            is Result.Success -> {
                reduce {
                    state.copy(
                        getTodosLoading = false,
                        origTodos = result.data.todos.map { it.content },
                        todoList = result.data.todos.map { it.content }
                    )
                }
            }

            is Result.Failure -> {

            }

            is Result.Exception -> {

            }
        }
    }
}