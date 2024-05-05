package com.teamegg.eggtart.domain.mandalart.usecases.cell

import com.teamegg.eggtart.common.util.ServerResult
import com.teamegg.eggtart.domain.mandalart.model.CellTodosModel
import com.teamegg.eggtart.domain.mandalart.repository.MandalartRemoteRepository
import javax.inject.Inject

/**
 * Created by 노원진 on 2024.04.26
 */

class GetMandalartCellDetailUseCase @Inject constructor(private val mandalartRepository: MandalartRemoteRepository) {

    suspend operator fun invoke(cellId: Long): ServerResult<CellTodosModel> = mandalartRepository.getCellDetail(cellId)
}