package com.teamegg.eggtart.features.write_goal

import com.teamegg.eggtart.domain.mandalart.model.CellTodosModel

/**
 * Created by 노원진 on 2024.03.31
 */

sealed class WriteGoalSideEffect {
    data object HideKeyboard : WriteGoalSideEffect()

    data object RequestFocus : WriteGoalSideEffect()

    data class FinishResult(val cellTodosModel: CellTodosModel? = null) : WriteGoalSideEffect()

    data class PopupDialog(val dialogTypes: DialogTypes) : WriteGoalSideEffect()
}