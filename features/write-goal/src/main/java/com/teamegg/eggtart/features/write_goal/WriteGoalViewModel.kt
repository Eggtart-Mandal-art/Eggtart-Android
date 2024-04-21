package com.teamegg.eggtart.features.write_goal

import androidx.lifecycle.ViewModel
import com.teamegg.eggtart.common.feature.util.GoalColorModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
class WriteGoalViewModel @Inject constructor() : ContainerHost<WriteGoalState, WriteGoalSideEffect>, ViewModel() {
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

    fun intentSetGoalColorModel(value: GoalColorModel) = intent {
        reduce {
            state.copy(goalColor = value, isShowBottomSheet = false)
        }
    }
}