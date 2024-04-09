package com.teamegg.eggtart.domain.mandalart.repository

import com.teamegg.eggtart.domain.mandalart.model.MandalartCellModel

/**
 *  Created by wonjin on 2024/03/28
 **/

interface MandalartCellRepository {
    suspend fun insertCell(vararg mandalartCell: MandalartCellModel, sheetId: Long)

    suspend fun deleteCell(vararg mandalartCell: MandalartCellModel, sheetId: Long)

    suspend fun updateCell(vararg mandalartCell: MandalartCellModel, sheetId: Long)

    suspend fun getMandalartCell(sheetId: Long, step: Int): List<MandalartCellModel>
}