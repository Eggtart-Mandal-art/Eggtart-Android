package com.teamegg.eggtart.features.home.mandalart

import com.teamegg.eggtart.domain.model.remote.ResponseMandalartCellModel

/**
 *  Created by wonjin on 2024/03/28
 **/

data class MandalartScreenState(
    val mandalartLoading: Boolean,
    val mandalartCellList: List<ResponseMandalartCellModel>,
)