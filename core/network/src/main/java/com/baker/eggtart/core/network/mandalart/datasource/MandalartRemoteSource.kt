package com.baker.eggtart.core.network.mandalart.datasource

import com.baker.eggtart.core.network.mandalart.entities.ReqUpdateCellEntity
import com.baker.eggtart.core.network.mandalart.entities.ResCellEntity
import com.baker.eggtart.core.network.mandalart.entities.ResCellTodosEntity

/**
 *  Created by wonjin on 2024/04/18
 **/

interface MandalartRemoteSource {
    suspend fun getSheets(): List<Long>

    suspend fun postCreateSheet(sheetName: String = ""): Long

    suspend fun getCells(sheetId: Long): List<ResCellEntity>

    suspend fun patchCell(cellId: Long, body: ReqUpdateCellEntity): ResCellTodosEntity

    suspend fun deleteCell(cellId: Long): ResCellTodosEntity

    suspend fun getCellDetail(cellId: Long): ResCellTodosEntity

    suspend fun getCellChildren(cellId: Long): List<ResCellEntity>
}