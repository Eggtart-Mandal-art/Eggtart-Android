package com.teamegg.eggtart.common.constants

import com.teamegg.eggtart.R
import com.teamegg.eggtart.common.model.GoalColorModel
import com.teamegg.eggtart.common.ui.theme.ColorBlueBerry
import com.teamegg.eggtart.common.ui.theme.ColorEggtart
import com.teamegg.eggtart.common.ui.theme.ColorGrape
import com.teamegg.eggtart.common.ui.theme.ColorLemon
import com.teamegg.eggtart.common.ui.theme.ColorLime
import com.teamegg.eggtart.common.ui.theme.ColorMelon
import com.teamegg.eggtart.common.ui.theme.ColorOlive
import com.teamegg.eggtart.common.ui.theme.ColorSausage
import com.teamegg.eggtart.common.ui.theme.ColorSoda

/**
 *  Created by wonjin on 2024/04/01
 **/
object Constants {
    val GOAL_COLORS = listOf(
        GoalColorModel(R.string.color_egg_tart, ColorEggtart),
        GoalColorModel(R.string.color_sausage, ColorSausage),
        GoalColorModel(R.string.color_lemon, ColorLemon),
        GoalColorModel(R.string.color_lime, ColorLime),
        GoalColorModel(R.string.color_soda, ColorSoda),
        GoalColorModel(R.string.color_grape, ColorGrape),
        GoalColorModel(R.string.color_blueberry, ColorBlueBerry),
        GoalColorModel(R.string.color_melon, ColorMelon),
        GoalColorModel(R.string.color_olive, ColorOlive)
    )
}