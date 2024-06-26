package com.baker.eggtart.domain.mandalart.model

/**
 *  Created by wonjin on 2024/03/28
 **/

data class SheetModel(
    val sheetId: Long,
    val ownerId: Long,
    val name: String,
    val createdAt: String,
    val modifiedAt: String,
    val depth1Cell: CellModel?
)