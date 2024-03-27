package com.eggtart.eggtart.source.local

import com.eggtart.eggtart.database.dao.MandalartCellDao
import com.eggtart.eggtart.model.local.MandalartCellModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by 노원진 on 2024.03.27
 */
interface MandalartCellLocalSource {
    suspend fun insertCell(vararg mandalartCell: MandalartCellModel)

    suspend fun getMandalartCell(sheetId: Long, depth: Int = 0): Flow<MandalartCellModel?>
}

class MandalartCellLocalSourceImpl @Inject constructor(private val mandalartCellDao: MandalartCellDao) : MandalartCellLocalSource {
    override suspend fun insertCell(vararg mandalartCell: MandalartCellModel) = mandalartCellDao.insert(*mandalartCell)

    override suspend fun getMandalartCell(sheetId: Long, depth: Int): Flow<MandalartCellModel?> = mandalartCellDao.getCell(sheetId, depth)
}