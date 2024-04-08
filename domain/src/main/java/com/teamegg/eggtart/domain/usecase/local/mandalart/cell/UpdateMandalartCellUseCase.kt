package com.teamegg.eggtart.domain.usecase.local.mandalart.cell

import com.teamegg.eggtart.domain.model.local.MandalartCellModel
import com.teamegg.eggtart.domain.repository.local.MandalartCellRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class UpdateMandalartCellUseCase @Inject constructor(private val mandalartCellRepository: MandalartCellRepository) {

    suspend operator fun invoke(vararg mandalartCell: MandalartCellModel) = mandalartCellRepository.updateCell(*mandalartCell)
}