package com.eggtart.eggtart.data.repository.local

import com.eggtart.eggtart.data.mapper.convert
import com.eggtart.eggtart.data.source.local.MandalartSheetLocalSource
import com.eggtart.eggtart.domain.model.local.MandalartSheetModel
import com.eggtart.eggtart.domain.repository.local.MandalartSheetRepository
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