package com.eggtart.eggtart.model.local

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 * Created by 노원진 on 2024.03.27
 */

@Entity(
    tableName = "mandalartCell",
    primaryKeys = arrayOf("sheetId", "id"),
    foreignKeys = arrayOf(ForeignKey(MandalartSheetModel::class, parentColumns = arrayOf("sheetId"), childColumns = arrayOf("sheetId")))
)
data class MandalartCellModel(
    val sheetId: Long,
    val id: Long,
    val color: Int,
    val goal: String,
    val order: Int,
    val depth: Int,
    val children: List<MandalartCellModel>
)