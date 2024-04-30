package com.teamegg.eggtart.domain.mandalart.usecases.sheet

import com.teamegg.eggtart.common.util.ServerResult
import com.teamegg.eggtart.domain.mandalart.repository.MandalartRemoteRepository
import javax.inject.Inject

/**
 * Created by 노원진 on 2024.04.21
 */
class CreateMandalartSheetUseCase @Inject constructor(private val mandalartRemoteRepository: MandalartRemoteRepository) {
    suspend operator fun invoke(sheetName: String = ""): ServerResult<Long> = mandalartRemoteRepository.postCreateSheet(sheetName = sheetName)
}