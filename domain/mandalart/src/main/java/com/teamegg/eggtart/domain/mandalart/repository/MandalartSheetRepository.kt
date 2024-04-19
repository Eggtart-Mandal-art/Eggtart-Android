package com.teamegg.eggtart.domain.mandalart.repository

import com.teamegg.eggtart.domain.mandalart.model.SheetModel
import kotlinx.coroutines.flow.Flow

/**
 *  Created by wonjin on 2024/03/28
 **/

interface MandalartSheetRepository {
    suspend fun insertSheet(vararg mandalartSheet: SheetModel)

    suspend fun updateSheet(vararg mandalartSheet: SheetModel)

    suspend fun deleteSheet(vararg mandalartSheet: SheetModel)

    suspend fun getSheets(): Flow<List<SheetModel>>
}