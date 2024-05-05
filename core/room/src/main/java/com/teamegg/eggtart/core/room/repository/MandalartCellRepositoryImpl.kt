package com.teamegg.eggtart.core.room.repository

import com.teamegg.eggtart.core.room.mapper.convert
import com.teamegg.eggtart.core.room.source.MandalartCellLocalSource
import com.teamegg.eggtart.domain.mandalart.model.CellModel
import com.teamegg.eggtart.domain.mandalart.repository.MandalartCellRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class MandalartCellRepositoryImpl @Inject constructor(private val mandalartCellLocalSource: MandalartCellLocalSource) : MandalartCellRepository {
    override suspend fun insertCell(vararg mandalartCell: CellModel, sheetId: Long) = mandalartCellLocalSource.insertCell(*(mandalartCell.map { it.convert(sheetId) }.toTypedArray()))

    override suspend fun deleteCell(vararg mandalartCell: CellModel, sheetId: Long) = mandalartCellLocalSource.deleteCell(*(mandalartCell.map { it.convert(sheetId) }.toTypedArray()))

    override suspend fun updateCell(vararg mandalartCell: CellModel, sheetId: Long) = mandalartCellLocalSource.updateCell(*(mandalartCell.map { it.convert(sheetId) }.toTypedArray()))

    override suspend fun getMandalartCell(sheetId: Long, step: Int): List<CellModel> = mandalartCellLocalSource.getMandalartCell(sheetId, step).map { it.convert() }
}