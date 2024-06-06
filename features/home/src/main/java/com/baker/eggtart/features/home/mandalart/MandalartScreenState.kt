package com.baker.eggtart.features.home.mandalart

import com.baker.eggtart.common.feature.components.ServerErrorDialogData
import com.baker.eggtart.domain.mandalart.model.CellModel

/**
 *  Created by wonjin on 2024/03/28
 **/

data class MandalartScreenState(
    val accessToken: String = "",
    val mandalartLoading: Boolean = false,
    val sheetIds: List<Long> = listOf(),
    val mandalartCellList: List<CellModel> = listOf(),
    val childCellList : List<CellModel> = listOf(),
    val serverErrorDialogData: ServerErrorDialogData? = null
)