package com.baker.eggtart.common.feature.util

import com.baker.eggtart.common.feature.R
import com.baker.eggtart.common.feature.theme.ColorBlueBerry
import com.baker.eggtart.common.feature.theme.ColorEggtart
import com.baker.eggtart.common.feature.theme.ColorGrape
import com.baker.eggtart.common.feature.theme.ColorLemon
import com.baker.eggtart.common.feature.theme.ColorLime
import com.baker.eggtart.common.feature.theme.ColorMelon
import com.baker.eggtart.common.feature.theme.ColorOlive
import com.baker.eggtart.common.feature.theme.ColorSausage
import com.baker.eggtart.common.feature.theme.ColorSoda

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
        GoalColorModel(R.string.color_olive, ColorOlive),
        GoalColorModel(R.string.color_blueberry, ColorBlueBerry),
        GoalColorModel(R.string.color_melon, ColorMelon),
    )
}