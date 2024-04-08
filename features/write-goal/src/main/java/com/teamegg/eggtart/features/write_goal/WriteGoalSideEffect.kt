package com.teamegg.eggtart.features.write_goal

/**
 * Created by 노원진 on 2024.03.31
 */

sealed class WriteGoalSideEffect {
    data object HideKeyboard : WriteGoalSideEffect()
}