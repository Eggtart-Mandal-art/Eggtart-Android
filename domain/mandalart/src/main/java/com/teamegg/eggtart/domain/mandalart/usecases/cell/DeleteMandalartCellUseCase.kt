package com.teamegg.eggtart.domain.mandalart.usecases.cell

import com.teamegg.eggtart.domain.mandalart.repository.MandalartRemoteRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class DeleteMandalartCellUseCase @Inject constructor(private val mandalartRemoteRepository: MandalartRemoteRepository) {

    suspend operator fun invoke(accessToken: String, cellId: Long) = mandalartRemoteRepository.deleteCell(accessToken = accessToken, cellId = cellId)
}