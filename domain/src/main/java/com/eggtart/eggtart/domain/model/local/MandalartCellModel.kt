package com.eggtart.eggtart.domain.model.local

/**
 *  Created by wonjin on 2024/03/28
 **/
data class MandalartCellModel(
    val sheetId: Long,
    val cellId: Long,
    val color: Long,
    val goal: String,
    val order: Int,
    val depth: Int,
    val children: List<MandalartCellModel>
)