package com.teamegg.eggtart.features.home.mandalart

import com.teamegg.eggtart.domain.mandalart.model.CellModel

/**
 *  Created by wonjin on 2024/03/28
 **/

data class MandalartScreenState(
    val mandalartLoading: Boolean,
    val mandalartCellList: List<CellModel>,
)