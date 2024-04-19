package com.teamegg.eggtart.core.network.mandalart.datasource

import com.teamegg.eggtart.core.network.mandalart.entities.PatchCellEntity
import com.teamegg.eggtart.domain.mandalart.model.UpdateCellModel

/**
 *  Created by wonjin on 2024/04/18
 **/

interface MandalartRemoteSource {
    suspend fun getSheets(accessToken: String): String

    suspend fun postCreateSheet(accessToken: String, sheetName: String = ""): String

    suspend fun getCells(accessToken: String, sheetId: Long, depth: Int, parentOrder: Int = 0): String

    suspend fun patchCell(accessToken: String, cellId: Long, body: PatchCellEntity): String
}