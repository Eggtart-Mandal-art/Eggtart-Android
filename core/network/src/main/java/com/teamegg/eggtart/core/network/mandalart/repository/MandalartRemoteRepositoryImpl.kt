package com.teamegg.eggtart.core.network.mandalart.repository

import com.teamegg.eggtart.common.util.Result
import com.teamegg.eggtart.common.util.ServerErrorModel
import com.teamegg.eggtart.core.network.mandalart.mapper.toPatchCellEntity
import com.teamegg.eggtart.core.network.mandalart.datasource.MandalartRemoteSource
import com.teamegg.eggtart.core.network.mandalart.entities.ResponseCreateSheetEntity
import com.teamegg.eggtart.domain.mandalart.model.CellModel
import com.teamegg.eggtart.domain.mandalart.model.UpdateCellModel
import com.teamegg.eggtart.domain.mandalart.repository.MandalartRemoteRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/18
 **/
class MandalartRemoteRepositoryImpl @Inject constructor(private val mandalartRemoteSource: MandalartRemoteSource) : MandalartRemoteRepository {
    override suspend fun getSheets(accessToken: String): Result<List<Long>> {
        val response = mandalartRemoteSource.getSheets(accessToken)

        return try {
            Result.Success(Json.decodeFromString<List<Long>>(response))
        } catch (e: Exception) {
            try {
                Result.Failure(Json.decodeFromString<ServerErrorModel>(response))
            } catch (e: Exception) {
                Result.Failure(null)
            }
        }
    }

    override suspend fun postCreateSheet(accessToken: String, sheetName: String): Result<Long> {
        val response = mandalartRemoteSource.postCreateSheet(accessToken, sheetName)

        return try {
            val result = Json.decodeFromString<ResponseCreateSheetEntity>(response)
            Result.Success(result.sheetId)
        } catch (e: Exception) {
            try {
                Result.Failure(Json.decodeFromString<ServerErrorModel>(response))
            } catch (e: Exception) {
                Result.Failure(null)
            }
        }
    }

    override suspend fun getCells(accessToken: String, sheetId: Long, depth: Int, parentOrder: Int): Result<List<CellModel>> {
        val response = mandalartRemoteSource.getCells(accessToken, sheetId, depth, parentOrder)

        return try {
            Result.Success(Json.decodeFromString<List<CellModel>>(response))
        } catch (e: Exception) {
            try {
                Result.Failure(Json.decodeFromString<ServerErrorModel>(response))
            } catch (e: Exception) {
                Result.Failure(null)
            }
        }
    }

    override suspend fun patchCell(accessToken: String, cellId: Long, body: UpdateCellModel): Result<CellModel> {
        val response = mandalartRemoteSource.patchCell(accessToken, cellId, body.toPatchCellEntity())

        return try {
            Result.Success(Json.decodeFromString<CellModel>(response))
        } catch (e: Exception) {
            try {
                Result.Failure(Json.decodeFromString<ServerErrorModel>(response))
            } catch (e: Exception) {
                Result.Failure(null)
            }
        }
    }
}