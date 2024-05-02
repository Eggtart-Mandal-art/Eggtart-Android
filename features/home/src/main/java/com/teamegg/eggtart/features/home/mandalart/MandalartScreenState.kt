package com.teamegg.eggtart.features.home.mandalart

import com.teamegg.eggtart.common.feature.components.ServerErrorDialogData
import com.teamegg.eggtart.domain.mandalart.model.CellModel

/**
 *  Created by wonjin on 2024/03/28
 **/

data class MandalartScreenState(
    val accessToken: String = "",
    val mandalartLoading: Boolean = false,
    val sheetIds: List<Long> = listOf(),
    val mandalartCellList: List<CellModel> = listOf(),
    val serverErrorDialogData: ServerErrorDialogData? = null
)