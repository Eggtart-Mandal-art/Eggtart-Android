package com.teamegg.eggtart.domain.mandalart.usecases.sheet

import com.teamegg.eggtart.domain.mandalart.model.SheetModel
import com.teamegg.eggtart.domain.mandalart.repository.MandalartSheetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class GetMandalartSheetsUseCase @Inject constructor(private val mandalartSheetRepository: MandalartSheetRepository) {
    suspend operator fun invoke(): Flow<List<SheetModel>> = mandalartSheetRepository.getSheets()
}