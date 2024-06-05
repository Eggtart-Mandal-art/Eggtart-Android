package com.baker.eggtart.core.room.entity

import androidx.room.Entity
import kotlinx.serialization.Serializable

/**
 * Created by 노원진 on 2024.03.27
 */

@Entity(tableName = "mandalartSheet", primaryKeys = ["sheetId"])
@Serializable
data class MandalartSheetEntity(
    val sheetId: Long,
    val ownerId: Long,
    val name: String,
    val createdAt: String,
    val modifiedAt: String,
    val depth1Cell: MandalartCellEntity?
)