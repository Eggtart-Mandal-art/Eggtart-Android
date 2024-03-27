package com.eggtart.eggtart.model.local

import androidx.room.Entity

/**
 * Created by 노원진 on 2024.03.27
 */

@Entity(tableName = "mandalartSheet", primaryKeys = arrayOf("sheetId"))
data class MandalartSheetModel(
    val sheetId: Long,
    val ownerId: Long,
    val name: String,
    val createdAt: String,
    val modifiedAt: String,
    val depth1Cell: MandalartCellModel
)