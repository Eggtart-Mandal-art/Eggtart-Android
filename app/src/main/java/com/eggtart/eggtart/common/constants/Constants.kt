package com.eggtart.eggtart.common.constants

import com.eggtart.eggtart.R
import com.eggtart.eggtart.common.model.GoalColorModel
import com.eggtart.eggtart.common.ui.ColorBlueBerry
import com.eggtart.eggtart.common.ui.ColorEggtart
import com.eggtart.eggtart.common.ui.ColorGrape
import com.eggtart.eggtart.common.ui.ColorLemon
import com.eggtart.eggtart.common.ui.ColorMelon
import com.eggtart.eggtart.common.ui.ColorOlive
import com.eggtart.eggtart.common.ui.ColorSausage
import com.eggtart.eggtart.common.ui.ColorSoda

/**
 *  Created by wonjin on 2024/04/01
 **/
object Constants {
    val GOAL_COLORS = listOf(
        GoalColorModel(R.string.color_egg_tart, ColorEggtart),
        GoalColorModel(R.string.color_sausage, ColorSausage),
        GoalColorModel(R.string.color_lemon, ColorLemon),
        GoalColorModel(R.string.color_soda, ColorSoda),
        GoalColorModel(R.string.color_grape, ColorGrape),
        GoalColorModel(R.string.color_blueberry, ColorBlueBerry),
        GoalColorModel(R.string.color_melon, ColorMelon),
        GoalColorModel(R.string.color_olive, ColorOlive)
    )
}