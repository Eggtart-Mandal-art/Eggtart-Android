package com.teamegg.eggtart.domain.mandalart.usecases.cell

import com.teamegg.eggtart.common.util.ServerResult
import com.teamegg.eggtart.domain.mandalart.model.CellModel
import com.teamegg.eggtart.domain.mandalart.repository.MandalartRemoteRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class GetMandalartCellUseCase @Inject constructor(private val mandalartRemoteRepository: MandalartRemoteRepository) {
    suspend operator fun invoke(
        sheetId: Long,
        depth: Int,
        parentOrder: Int
    ): ServerResult<List<CellModel>> = mandalartRemoteRepository.getCells(
        sheetId = sheetId,
        depth = depth,
        parentOrder = parentOrder
    )
}