package com.eggtart.eggtart.data.repository.local

import com.eggtart.eggtart.data.mapper.convert
import com.eggtart.eggtart.data.source.local.MandalartCellLocalSource
import com.eggtart.eggtart.domain.model.local.MandalartCellModel
import com.eggtart.eggtart.domain.repository.local.MandalartCellRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class MandalartCellRepositoryImpl @Inject constructor(private val mandalartCellLocalSource: MandalartCellLocalSource) : MandalartCellRepository {
    override suspend fun insertCell(vararg mandalartCell: MandalartCellModel) = mandalartCellLocalSource.insertCell(*(mandalartCell.map(MandalartCellModel::convert).toTypedArray()))

    override suspend fun deleteCell(vararg mandalartCell: MandalartCellModel) = mandalartCellLocalSource.deleteCell(*(mandalartCell.map(MandalartCellModel::convert).toTypedArray()))

    override suspend fun updateCell(vararg mandalartCell: MandalartCellModel) = mandalartCellLocalSource.updateCell(*(mandalartCell.map(MandalartCellModel::convert).toTypedArray()))

    override suspend fun getMandalartCell(sheetId: Long, depth: Int): Flow<MandalartCellModel?> = mandalartCellLocalSource.getMandalartCell(sheetId, depth).map { it?.convert() }
}