package com.teamegg.eggtart.domain.mandalart.repository

import com.teamegg.eggtart.common.util.ServerResult
import com.teamegg.eggtart.domain.mandalart.model.CellModel
import com.teamegg.eggtart.domain.mandalart.model.CellTodosModel
import com.teamegg.eggtart.domain.mandalart.model.UpdateCellModel

/**
 *  Created by wonjin on 2024/04/18
 **/

interface MandalartRemoteRepository {
    suspend fun getSheets(): ServerResult<List<Long>>

    suspend fun postCreateSheet(sheetName: String = ""): ServerResult<Long>

    suspend fun getCells(sheetId: Long, depth: Int, parentOrder: Int = 0): ServerResult<List<CellModel>>

    suspend fun patchCell(cellId: Long, body: UpdateCellModel): ServerResult<CellTodosModel>

    suspend fun deleteCell(cellId: Long): ServerResult<CellTodosModel>

    suspend fun getCellDetail(cellId: Long): ServerResult<CellTodosModel>
}