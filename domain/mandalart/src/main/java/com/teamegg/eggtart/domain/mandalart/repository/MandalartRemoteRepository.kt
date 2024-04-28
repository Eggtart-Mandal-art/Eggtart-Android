package com.teamegg.eggtart.domain.mandalart.repository

import com.teamegg.eggtart.common.util.Result
import com.teamegg.eggtart.domain.mandalart.model.ResCellModel
import com.teamegg.eggtart.domain.mandalart.model.ResCellTodosModel
import com.teamegg.eggtart.domain.mandalart.model.UpdateCellModel

/**
 *  Created by wonjin on 2024/04/18
 **/

interface MandalartRemoteRepository {
    suspend fun getSheets(accessToken: String): Result<List<Long>>

    suspend fun postCreateSheet(accessToken: String, sheetName: String = ""): Result<Long>

    suspend fun getCells(accessToken: String, sheetId: Long, depth: Int, parentOrder: Int = 0): Result<List<ResCellModel>>

    suspend fun patchCell(accessToken: String, cellId: Long, body: UpdateCellModel): Result<ResCellTodosModel>

    suspend fun deleteCell(accessToken: String, cellId: Long): Result<ResCellTodosModel>

    suspend fun getCellDetail(accessToken: String, cellId: Long): Result<ResCellTodosModel>
}