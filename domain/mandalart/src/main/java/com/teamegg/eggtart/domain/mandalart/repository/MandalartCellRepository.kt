package com.teamegg.eggtart.domain.mandalart.repository

import com.teamegg.eggtart.domain.mandalart.model.ResCellModel

/**
 *  Created by wonjin on 2024/03/28
 **/

interface MandalartCellRepository {
    suspend fun insertCell(vararg mandalartCell: ResCellModel, sheetId: Long)

    suspend fun deleteCell(vararg mandalartCell: ResCellModel, sheetId: Long)

    suspend fun updateCell(vararg mandalartCell: ResCellModel, sheetId: Long)

    suspend fun getMandalartCell(sheetId: Long, step: Int): List<ResCellModel>
}