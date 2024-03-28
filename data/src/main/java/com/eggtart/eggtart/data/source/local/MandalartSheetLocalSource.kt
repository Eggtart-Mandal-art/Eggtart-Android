package com.eggtart.eggtart.data.source.local

import com.eggtart.eggtart.data.database.dao.MandalartSheetDao
import com.eggtart.eggtart.data.entity.local.MandalartSheetEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by 노원진 on 2024.03.27
 */
interface MandalartSheetLocalSource {

    suspend fun insertSheet(vararg mandalartSheet: MandalartSheetEntity)

    suspend fun updateSheet(vararg mandalartSheet: MandalartSheetEntity)

    suspend fun deleteSheet(vararg mandalartSheet: MandalartSheetEntity)

    suspend fun getSheets(): Flow<List<MandalartSheetEntity>>
}

class MandalartSheetLocalSourceImpl @Inject constructor(private val mandalartSheetDao: MandalartSheetDao) : MandalartSheetLocalSource {
    override suspend fun insertSheet(vararg mandalartSheet: MandalartSheetEntity) = mandalartSheetDao.insertSheet(*mandalartSheet)

    override suspend fun updateSheet(vararg mandalartSheet: MandalartSheetEntity) = mandalartSheetDao.updateSheet(*mandalartSheet)

    override suspend fun deleteSheet(vararg mandalartSheet: MandalartSheetEntity) = mandalartSheetDao.deleteSheet(*mandalartSheet)

    override suspend fun getSheets(): Flow<List<MandalartSheetEntity>> = mandalartSheetDao.getSheets()
}