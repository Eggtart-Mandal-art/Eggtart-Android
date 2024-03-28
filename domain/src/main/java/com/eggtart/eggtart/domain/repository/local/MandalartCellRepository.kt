package com.eggtart.eggtart.domain.repository.local

import com.eggtart.eggtart.domain.model.local.MandalartCellModel
import kotlinx.coroutines.flow.Flow

/**
 *  Created by wonjin on 2024/03/28
 **/

interface MandalartCellRepository {
    suspend fun insertCell(vararg mandalartCell: MandalartCellModel)

    suspend fun deleteCell(vararg mandalartCell: MandalartCellModel)

    suspend fun updateCell(vararg mandalartCell: MandalartCellModel)

    suspend fun getMandalartCell(sheetId: Long, depth: Int): Flow<MandalartCellModel?>
}