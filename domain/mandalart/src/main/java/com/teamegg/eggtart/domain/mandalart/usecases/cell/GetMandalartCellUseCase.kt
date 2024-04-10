package com.teamegg.eggtart.domain.mandalart.usecases.cell

import com.teamegg.eggtart.domain.mandalart.model.MandalartCellModel
import com.teamegg.eggtart.domain.mandalart.repository.MandalartCellRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class GetMandalartCellUseCase @Inject constructor(private val mandalartCellRepository: MandalartCellRepository) {
    suspend operator fun invoke(sheetId: Long, step: Int = 0): List<MandalartCellModel> = mandalartCellRepository.getMandalartCell(sheetId, step)
}