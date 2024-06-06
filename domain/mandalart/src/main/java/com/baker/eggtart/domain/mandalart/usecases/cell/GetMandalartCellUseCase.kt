package com.baker.eggtart.domain.mandalart.usecases.cell

import com.baker.eggtart.common.util.ServerResult
import com.baker.eggtart.domain.mandalart.model.CellModel
import com.baker.eggtart.domain.mandalart.repository.MandalartRemoteRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class GetMandalartCellUseCase @Inject constructor(private val mandalartRemoteRepository: MandalartRemoteRepository) {
    suspend operator fun invoke(sheetId: Long): ServerResult<List<CellModel>> = mandalartRemoteRepository.getCells(sheetId = sheetId)
}