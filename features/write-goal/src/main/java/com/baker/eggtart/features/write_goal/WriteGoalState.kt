package com.baker.eggtart.features.write_goal

import com.baker.eggtart.common.feature.components.DialogData
import com.baker.eggtart.common.feature.components.ServerErrorDialogData
import com.baker.eggtart.common.feature.util.GoalColorModel

/**
 * Created by 노원진 on 2024.03.31
 */

data class WriteGoalState(
    val updateCellLoading: Boolean = false,
    val getTodosLoading: Boolean = false,
    val imeBottom: Int = 0,
    val goalString: String = "",
    val goalColor: GoalColorModel? = null,
    val isShowBottomSheet: Boolean = false,
    val origTodos: List<String> = listOf(),
    val todoList: List<String> = listOf(),
    val dialogData: DialogData? = null,
    val serverErrorDialogData: ServerErrorDialogData? = null
)