package com.teamegg.eggtart.features.goal.write

import androidx.lifecycle.ViewModel
import com.teamegg.eggtart.common.model.GoalColorModel
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun intentSetImeBottom(value: Int) = intent {
        reduce {
            state.copy(imeBottom = value)
        }

        if (value == 0) {
            postSideEffect(sideEffect = WriteGoalSideEffect.HideKeyboard)
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