package com.teamegg.eggtart.domain.mandalart.usecases.sheet

import com.teamegg.eggtart.domain.mandalart.model.SheetModel
import com.teamegg.eggtart.domain.mandalart.repository.MandalartSheetRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class UpdateMandalartSheetUseCase @Inject constructor(private val mandalartSheetRepository: MandalartSheetRepository) {
    suspend operator fun invoke(vararg mandalartSheet: SheetModel) = mandalartSheetRepository.updateSheet(*mandalartSheet)
}