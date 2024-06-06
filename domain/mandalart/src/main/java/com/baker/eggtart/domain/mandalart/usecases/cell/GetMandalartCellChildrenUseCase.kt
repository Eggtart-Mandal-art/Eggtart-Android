package com.baker.eggtart.domain.mandalart.usecases.cell

import com.baker.eggtart.common.util.ServerResult
import com.baker.eggtart.domain.mandalart.model.CellModel
import com.baker.eggtart.domain.mandalart.repository.MandalartRemoteRepository
import javax.inject.Inject

/**
 * Created by 노원진 on 2024.05.12
 */
class GetMandalartCellChildrenUseCase @Inject constructor(
    private val manadalartRemoteRepository: MandalartRemoteRepository
) {
    suspend operator fun invoke(cellId: Long): ServerResult<List<CellModel>> = manadalartRemoteRepository.getCellChildren(cellId)
}