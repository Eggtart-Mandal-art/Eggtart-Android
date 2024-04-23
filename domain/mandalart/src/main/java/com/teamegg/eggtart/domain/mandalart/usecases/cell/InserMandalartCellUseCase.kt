package com.teamegg.eggtart.domain.mandalart.usecases.cell

import com.teamegg.eggtart.domain.mandalart.model.ResCellModel
import com.teamegg.eggtart.domain.mandalart.repository.MandalartCellRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class InserMandalartCellUseCase @Inject constructor(private val mandalartCellRepository: MandalartCellRepository) {
    suspend operator fun invoke(vararg mandalartCell: ResCellModel, sheetId: Long) = mandalartCellRepository.insertCell(*mandalartCell, sheetId = sheetId)
}