package com.teamegg.eggtart.core.room.repository

import com.teamegg.eggtart.core.room.mapper.convert
import com.teamegg.eggtart.core.room.source.MandalartCellLocalSource
import com.teamegg.eggtart.domain.mandalart.model.MandalartCellModel
import com.teamegg.eggtart.domain.mandalart.repository.MandalartCellRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class MandalartCellRepositoryImpl @Inject constructor(private val mandalartCellLocalSource: MandalartCellLocalSource) : MandalartCellRepository {
    override suspend fun insertCell(vararg mandalartCell: MandalartCellModel, sheetId: Long) = mandalartCellLocalSource.insertCell(*(mandalartCell.map { it.convert(sheetId) }.toTypedArray()))

    override suspend fun deleteCell(vararg mandalartCell: MandalartCellModel, sheetId: Long) = mandalartCellLocalSource.deleteCell(*(mandalartCell.map { it.convert(sheetId) }.toTypedArray()))

    override suspend fun updateCell(vararg mandalartCell: MandalartCellModel, sheetId: Long) = mandalartCellLocalSource.updateCell(*(mandalartCell.map { it.convert(sheetId) }.toTypedArray()))

    override suspend fun getMandalartCell(sheetId: Long, depth: Int): Flow<MandalartCellModel?> = mandalartCellLocalSource.getMandalartCell(sheetId, depth).map { it?.convert() }
}