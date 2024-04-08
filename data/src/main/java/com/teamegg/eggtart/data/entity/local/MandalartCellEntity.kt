package com.teamegg.eggtart.data.entity.local

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 * Created by 노원진 on 2024.03.27
 */

@Entity(
    tableName = "mandalartCell",
    primaryKeys = ["sheetId", "cellId"],
    foreignKeys = [ForeignKey(MandalartSheetEntity::class, parentColumns = ["sheetId"], childColumns = ["sheetId"])]
)
data class MandalartCellEntity(
    val sheetId: Long,
    val cellId: Long,
    val color: Long,
    val goal: String,
    val order: Int,
    val depth: Int,
    val children: List<MandalartCellEntity>
)