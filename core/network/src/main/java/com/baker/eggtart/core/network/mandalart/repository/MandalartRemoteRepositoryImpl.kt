package com.baker.eggtart.core.network.mandalart.repository

import com.baker.eggtart.common.util.ServerResult
import com.baker.eggtart.core.network.mandalart.datasource.MandalartRemoteSource
import com.baker.eggtart.core.network.mandalart.entities.ResCellEntity
import com.baker.eggtart.core.network.mandalart.mapper.toCellModel
import com.baker.eggtart.core.network.mandalart.mapper.toCellTodosModel
import com.baker.eggtart.core.network.mandalart.mapper.toPatchCellEntity
import com.baker.eggtart.domain.mandalart.model.CellModel
import com.baker.eggtart.domain.mandalart.model.CellTodosModel
import com.baker.eggtart.domain.mandalart.model.UpdateCellModel
import com.baker.eggtart.domain.mandalart.repository.MandalartRemoteRepository
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/18
 **/
class MandalartRemoteRepositoryImpl @Inject constructor(private val mandalartRemoteSource: MandalartRemoteSource) : MandalartRemoteRepository {
    override suspend fun getSheets(): ServerResult<List<Long>> = try {
        val response = mandalartRemoteSource.getSheets()

        ServerResult.Success(response)
    } catch (e: Exception) {
        ServerResult.parseException(e)
    }

    override suspend fun postCreateSheet(sheetName: String): ServerResult<Long> = try {
        val response = mandalartRemoteSource.postCreateSheet(sheetName)

        ServerResult.Success(response)
    } catch (e: Exception) {
        ServerResult.parseException(e)
    }

    override suspend fun getCells(sheetId: Long, depth: Int, parentOrder: Int): ServerResult<List<CellModel>> = try {
        val response = mandalartRemoteSource.getCells(sheetId, depth, parentOrder)

        ServerResult.Success(response.map(ResCellEntity::toCellModel))
    } catch (e: Exception) {
        ServerResult.parseException(e)
    }

    override suspend fun patchCell(cellId: Long, body: UpdateCellModel): ServerResult<CellTodosModel> = try {
        val response = mandalartRemoteSource.patchCell(cellId, body.toPatchCellEntity())

        ServerResult.Success(response.toCellTodosModel())
    } catch (e: Exception) {
        ServerResult.parseException(e)
    }

    override suspend fun deleteCell(cellId: Long): ServerResult<CellTodosModel> = try {
        val response = mandalartRemoteSource.deleteCell(cellId)

        ServerResult.Success(response.toCellTodosModel())
    } catch (e: Exception) {
        ServerResult.parseException(e)
    }

    override suspend fun getCellDetail(cellId: Long): ServerResult<CellTodosModel> = try {
        val response = mandalartRemoteSource.getCellDetail(cellId)

        ServerResult.Success(response.toCellTodosModel())
    } catch (e: Exception) {
        ServerResult.parseException(e)
    }
}