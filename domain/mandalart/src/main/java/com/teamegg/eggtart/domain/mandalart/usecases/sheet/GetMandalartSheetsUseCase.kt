package com.teamegg.eggtart.domain.mandalart.usecases.sheet

import com.teamegg.eggtart.common.util.ServerResult
import com.teamegg.eggtart.domain.mandalart.repository.MandalartRemoteRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class GetMandalartSheetsUseCase @Inject constructor(private val mandalartRemoteRepository: MandalartRemoteRepository) {
    suspend operator fun invoke(): ServerResult<List<Long>> = mandalartRemoteRepository.getSheets()
}