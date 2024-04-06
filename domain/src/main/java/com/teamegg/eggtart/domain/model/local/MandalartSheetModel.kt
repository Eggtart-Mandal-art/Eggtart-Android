package com.teamegg.eggtart.domain.model.local

/**
 *  Created by wonjin on 2024/03/28
 **/

data class MandalartSheetModel (
    val sheetId: Long,
    val ownerId: Long,
    val name: String,
    val createdAt: String,
    val modifiedAt: String,
    val depth1Cell: MandalartCellModel?
)