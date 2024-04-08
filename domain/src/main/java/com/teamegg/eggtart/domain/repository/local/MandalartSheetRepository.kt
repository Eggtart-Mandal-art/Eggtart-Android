package com.teamegg.eggtart.domain.repository.local

import com.teamegg.eggtart.domain.model.local.MandalartSheetModel
import kotlinx.coroutines.flow.Flow

/**
 *  Created by wonjin on 2024/03/28
 **/

interface MandalartSheetRepository {
    suspend fun insertSheet(vararg mandalartSheet: MandalartSheetModel)

    suspend fun updateSheet(vararg mandalartSheet: MandalartSheetModel)

    suspend fun deleteSheet(vararg mandalartSheet: MandalartSheetModel)

    suspend fun getSheets(): Flow<List<MandalartSheetModel>>
}