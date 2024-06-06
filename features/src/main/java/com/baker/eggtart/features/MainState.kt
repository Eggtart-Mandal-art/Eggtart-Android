package com.baker.eggtart.features

import com.baker.eggtart.common.feature.components.ServerErrorDialogData

/**
 *  Created by wonjin on 2024/03/29
 **/

data class MainState(
    val initialized: Boolean = false,
    val sheetIds: List<Long> = listOf(),
    val serverErrorDialogData: ServerErrorDialogData? = null
)