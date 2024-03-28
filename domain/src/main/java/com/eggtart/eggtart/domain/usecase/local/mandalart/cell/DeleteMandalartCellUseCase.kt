package com.eggtart.eggtart.domain.usecase.local.mandalart.cell

import com.eggtart.eggtart.domain.model.local.MandalartCellModel
import com.eggtart.eggtart.domain.repository.local.MandalartCellRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class DeleteMandalartCellUseCase @Inject constructor(private val mandalartCellRepository: MandalartCellRepository) {

    suspend operator fun invoke(vararg mandalartCell: MandalartCellModel) = mandalartCellRepository.deleteCell(*mandalartCell)
}