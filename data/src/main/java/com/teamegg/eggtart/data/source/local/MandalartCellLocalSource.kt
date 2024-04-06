package com.teamegg.eggtart.data.source.local

import com.teamegg.eggtart.data.database.dao.MandalartCellDao
import com.teamegg.eggtart.data.entity.local.MandalartCellEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by 노원진 on 2024.03.27
 */
interface MandalartCellLocalSource {
    suspend fun insertCell(vararg mandalartCell: MandalartCellEntity)

    suspend fun deleteCell(vararg mandalartCell: MandalartCellEntity)

    suspend fun updateCell(vararg mandalartCell: MandalartCellEntity)

    suspend fun getMandalartCell(sheetId: Long, depth: Int): Flow<MandalartCellEntity?>
}

class MandalartCellLocalSourceImpl @Inject constructor(private val mandalartCellDao: MandalartCellDao) : MandalartCellLocalSource {
    override suspend fun insertCell(vararg mandalartCell: MandalartCellEntity) = mandalartCellDao.insertCell(*mandalartCell)

    override suspend fun deleteCell(vararg mandalartCell: MandalartCellEntity) = mandalartCellDao.deleteCell(*mandalartCell)

    override suspend fun updateCell(vararg mandalartCell: MandalartCellEntity) = mandalartCellDao.updateCell(*mandalartCell)

    override suspend fun getMandalartCell(sheetId: Long, depth: Int): Flow<MandalartCellEntity?> = mandalartCellDao.getCell(sheetId, depth)
}