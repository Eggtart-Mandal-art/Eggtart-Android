package com.teamegg.eggtart.core.network.mandalart.datasource

import com.teamegg.eggtart.common.util.ServerResult
import com.teamegg.eggtart.core.network.mandalart.entities.ReqUpdateCellEntity
import com.teamegg.eggtart.core.network.mandalart.entities.ResCellEntity
import com.teamegg.eggtart.core.network.mandalart.entities.ResCellTodosEntity

/**
 *  Created by wonjin on 2024/04/18
 **/

interface MandalartRemoteSource {
    suspend fun getSheets(): List<Long>

    suspend fun postCreateSheet(sheetName: String = ""): Long

    suspend fun getCells(sheetId: Long, depth: Int, parentOrder: Int = 0): List<ResCellEntity>

    suspend fun patchCell(cellId: Long, body: ReqUpdateCellEntity): ResCellTodosEntity

    suspend fun deleteCell(cellId: Long): ResCellTodosEntity

    suspend fun getCellDetail(cellId: Long): ResCellTodosEntity
}