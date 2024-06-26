package com.baker.eggtart.common.feature.util

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

/**
 *  Created by wonjin on 2024/04/01
 **/

data class GoalColorModel(
    @StringRes val colorNameId: Int,
    val color: Color
)