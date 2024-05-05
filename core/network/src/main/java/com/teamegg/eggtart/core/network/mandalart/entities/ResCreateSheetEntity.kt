package com.teamegg.eggtart.core.network.mandalart.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *  Created by wonjin on 2024/04/18
 **/

@Serializable
data class ResCreateSheetEntity(
    @SerialName("sheet_id")
    val sheetId: Long
)