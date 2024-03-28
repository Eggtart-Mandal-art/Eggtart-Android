package com.eggtart.eggtart.data.entity.local

import androidx.room.Entity

/**
 * Created by 노원진 on 2024.03.27
 */

@Entity(tableName = "mandalartSheet", primaryKeys = ["sheetId"])
data class MandalartSheetEntity(
    val sheetId: Long,
    val ownerId: Long,
    val name: String,
    val createdAt: String,
    val modifiedAt: String,
    val depth1Cell: MandalartCellEntity?
)