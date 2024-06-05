package com.baker.eggtart.core.room.repository

import com.baker.eggtart.core.room.mapper.convert
import com.baker.eggtart.core.room.source.MandalartSheetLocalSource
import com.baker.eggtart.domain.mandalart.model.SheetModel
import com.baker.eggtart.domain.mandalart.repository.MandalartSheetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class MandalartSheetRepositoryImpl @Inject constructor(private val mandalartSheetLocalSource: MandalartSheetLocalSource) : MandalartSheetRepository {
    override suspend fun insertSheet(vararg mandalartSheet: SheetModel) = mandalartSheetLocalSource.insertSheet(*(mandalartSheet.map { it.convert() }.toTypedArray()))

    override suspend fun updateSheet(vararg mandalartSheet: SheetModel) = mandalartSheetLocalSource.updateSheet(*(mandalartSheet.map { it.convert() }.toTypedArray()))

    override suspend fun deleteSheet(vararg mandalartSheet: SheetModel) = mandalartSheetLocalSource.deleteSheet(*(mandalartSheet.map { it.convert() }.toTypedArray()))

    override suspend fun getSheets(): Flow<List<SheetModel>> = mandalartSheetLocalSource.getSheets().map { sheets ->
        sheets.map {
            it.convert()
        }
    }
}