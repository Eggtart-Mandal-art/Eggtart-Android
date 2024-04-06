package com.teamegg.eggtart.domain.usecase.local.mandalart.sheet

import com.teamegg.eggtart.domain.model.local.MandalartSheetModel
import com.teamegg.eggtart.domain.repository.local.MandalartSheetRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class UpdateMandalartSheetUseCase @Inject constructor(private val mandalartSheetRepository: MandalartSheetRepository) {
    suspend operator fun invoke(vararg mandalartSheet: MandalartSheetModel) = mandalartSheetRepository.updateSheet(*mandalartSheet)
}