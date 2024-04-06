package com.teamegg.eggtart.features.goal.write

import com.teamegg.eggtart.common.model.GoalColorModel

/**
 * Created by 노원진 on 2024.03.31
 */

data class WriteGoalState(
    val imeBottom: Int = 0,
    val goalString: String = "",
    val goalColor: GoalColorModel? = null,
    val isShowBottomSheet: Boolean = false
)