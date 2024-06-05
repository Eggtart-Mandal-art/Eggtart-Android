package com.baker.eggtart.domain.mandalart.repository

import com.baker.eggtart.domain.mandalart.model.CellModel

/**
 *  Created by wonjin on 2024/03/28
 **/

interface MandalartCellRepository {
    suspend fun insertCell(vararg mandalartCell: CellModel, sheetId: Long)

    suspend fun deleteCell(vararg mandalartCell: CellModel, sheetId: Long)

    suspend fun updateCell(vararg mandalartCell: CellModel, sheetId: Long)

    suspend fun getMandalartCell(sheetId: Long, step: Int): List<CellModel>
}