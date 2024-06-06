package com.baker.eggtart.core.room.source

import com.baker.eggtart.core.room.database.dao.MandalartCellDao
import com.baker.eggtart.core.room.entity.MandalartCellEntity
import javax.inject.Inject

/**
 * Created by 노원진 on 2024.03.27
 */
interface MandalartCellLocalSource {
    suspend fun insertCell(vararg mandalartCell: MandalartCellEntity)

    suspend fun deleteCell(vararg mandalartCell: MandalartCellEntity)

    suspend fun updateCell(vararg mandalartCell: MandalartCellEntity)

    suspend fun getMandalartCell(sheetId: Long, step: Int): List<MandalartCellEntity>
}

class MandalartCellLocalSourceImpl @Inject constructor(private val mandalartCellDao: MandalartCellDao) : MandalartCellLocalSource {
    override suspend fun insertCell(vararg mandalartCell: MandalartCellEntity) = mandalartCellDao.insertCell(*mandalartCell)

    override suspend fun deleteCell(vararg mandalartCell: MandalartCellEntity) = mandalartCellDao.deleteCell(*mandalartCell)

    override suspend fun updateCell(vararg mandalartCell: MandalartCellEntity) = mandalartCellDao.updateCell(*mandalartCell)

    override suspend fun getMandalartCell(sheetId: Long, step: Int): List<MandalartCellEntity> = mandalartCellDao.getCell(sheetId, step)
}