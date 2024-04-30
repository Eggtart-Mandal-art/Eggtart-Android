package com.teamegg.eggtart.domain.mandalart.usecases.cell

import com.teamegg.eggtart.domain.mandalart.model.UpdateCellModel
import com.teamegg.eggtart.domain.mandalart.repository.MandalartRemoteRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class UpdateMandalartCellUseCase @Inject constructor(private val mandalartRemoteRepository: MandalartRemoteRepository) {

    suspend operator fun invoke(cellId: Long, updateCellModel: UpdateCellModel) =
        mandalartRemoteRepository.patchCell(cellId = cellId, body = updateCellModel)
}