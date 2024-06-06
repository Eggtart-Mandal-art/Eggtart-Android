package com.baker.eggtart.core.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import kotlinx.serialization.Serializable

/**
 * Created by 노원진 on 2024.03.27
 */

@Entity(
    tableName = "mandalartCell",
    primaryKeys = ["sheetId", "id"],
    foreignKeys = [ForeignKey(MandalartSheetEntity::class, parentColumns = ["sheetId"], childColumns = ["sheetId"])]
)
@Serializable
data class MandalartCellEntity(
    val sheetId: Long,
    val step: Int,
    val order: Int,
    val id: Int,
    val color: Long? = null,
    val goal: String? = null
)