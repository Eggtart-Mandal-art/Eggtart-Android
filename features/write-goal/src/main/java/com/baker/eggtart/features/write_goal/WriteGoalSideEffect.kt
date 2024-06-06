package com.baker.eggtart.features.write_goal

import com.baker.eggtart.common.util.ServerResult
import com.baker.eggtart.domain.mandalart.model.CellTodosModel

/**
 * Created by 노원진 on 2024.03.31
 */

sealed class WriteGoalSideEffect {
    data object HideKeyboard : WriteGoalSideEffect()

    data object RequestFocus : WriteGoalSideEffect()

    data class FinishResult(val cellTodosModel: CellTodosModel? = null) : WriteGoalSideEffect()

    data class PopupDialog(val popupType: PopupType) : WriteGoalSideEffect()

    data class ServerErrorPopup(val type: ServerCallType, val serverResult: ServerResult<*>) : WriteGoalSideEffect()
}

enum class ServerCallType {
    UPDATE_CELL, DELETE_CELL, GET_CELL_DETAIL
}

enum class PopupType {
    WITHOUT_SAVE_FINISH, DELETE_CELL
}