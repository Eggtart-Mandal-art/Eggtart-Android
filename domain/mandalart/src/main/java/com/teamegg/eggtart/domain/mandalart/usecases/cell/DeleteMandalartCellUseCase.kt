package com.teamegg.eggtart.domain.mandalart.usecases.cell

import com.teamegg.eggtart.domain.mandalart.model.CellModel
import com.teamegg.eggtart.domain.mandalart.repository.MandalartCellRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class DeleteMandalartCellUseCase @Inject constructor(private val mandalartCellRepository: MandalartCellRepository) {

    suspend operator fun invoke(vararg mandalartCell: CellModel, sheetId: Long) = mandalartCellRepository.deleteCell(*mandalartCell, sheetId = sheetId)
}