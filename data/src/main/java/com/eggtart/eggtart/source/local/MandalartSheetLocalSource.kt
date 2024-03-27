package com.eggtart.eggtart.source.local

import com.eggtart.eggtart.database.dao.MandalartSheetDao
import com.eggtart.eggtart.model.local.MandalartSheetModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by 노원진 on 2024.03.27
 */
interface MandalartSheetLocalSource {

    suspend fun insertSheet(vararg mandalartSheet: MandalartSheetModel)

    suspend fun getSheets(): Flow<List<MandalartSheetModel>>
}

class MandalartSheetLocalSourceImpl @Inject constructor(private val mandalartSheetDao: MandalartSheetDao): MandalartSheetLocalSource {
    override suspend fun insertSheet(vararg mandalartSheet: MandalartSheetModel) = mandalartSheetDao.insert(*mandalartSheet)

    override suspend fun getSheets(): Flow<List<MandalartSheetModel>> = mandalartSheetDao.getSheets()
}