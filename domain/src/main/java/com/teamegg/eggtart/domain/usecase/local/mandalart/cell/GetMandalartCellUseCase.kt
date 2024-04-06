package com.teamegg.eggtart.domain.usecase.local.mandalart.cell

import com.teamegg.eggtart.domain.model.local.MandalartCellModel
import com.teamegg.eggtart.domain.repository.local.MandalartCellRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class GetMandalartCellUseCase @Inject constructor(private val mandalartCellRepository: MandalartCellRepository) {
    suspend operator fun invoke(sheetId: Long, depth: Int = 0): Flow<MandalartCellModel?> = mandalartCellRepository.getMandalartCell(sheetId, depth)
}