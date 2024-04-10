package com.teamegg.eggtart.core.room.repository

import com.teamegg.eggtart.core.room.mapper.convert
import com.teamegg.eggtart.core.room.source.MandalartSheetLocalSource
import com.teamegg.eggtart.domain.mandalart.model.MandalartSheetModel
import com.teamegg.eggtart.domain.mandalart.repository.MandalartSheetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class MandalartSheetRepositoryImpl @Inject constructor(private val mandalartSheetLocalSource: MandalartSheetLocalSource) : MandalartSheetRepository {
    override suspend fun insertSheet(vararg mandalartSheet: MandalartSheetModel) = mandalartSheetLocalSource.insertSheet(*(mandalartSheet.map { it.convert() }.toTypedArray()))

    override suspend fun updateSheet(vararg mandalartSheet: MandalartSheetModel) = mandalartSheetLocalSource.updateSheet(*(mandalartSheet.map { it.convert() }.toTypedArray()))

    override suspend fun deleteSheet(vararg mandalartSheet: MandalartSheetModel) = mandalartSheetLocalSource.deleteSheet(*(mandalartSheet.map { it.convert() }.toTypedArray()))

    override suspend fun getSheets(): Flow<List<MandalartSheetModel>> = mandalartSheetLocalSource.getSheets().map { sheets ->
        sheets.map {
            it.convert()
        }
    }
}