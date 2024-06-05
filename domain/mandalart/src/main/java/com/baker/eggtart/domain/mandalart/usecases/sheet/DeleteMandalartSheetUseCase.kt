package com.baker.eggtart.domain.mandalart.usecases.sheet

import com.baker.eggtart.domain.mandalart.model.SheetModel
import com.baker.eggtart.domain.mandalart.repository.MandalartSheetRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/03/28
 **/

class DeleteMandalartSheetUseCase @Inject constructor(private val mandalartSheetRepository: MandalartSheetRepository) {
    suspend operator fun invoke(vararg mandalartSheet: SheetModel) = mandalartSheetRepository.deleteSheet(*mandalartSheet)
}