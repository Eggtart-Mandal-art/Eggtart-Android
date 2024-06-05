package com.baker.eggtart.core.network.mandalart.datasource

import com.baker.eggtart.common.util.KtorTokenClient
import com.baker.eggtart.core.network.mandalart.entities.ReqCreateSheetEntity
import com.baker.eggtart.core.network.mandalart.entities.ReqUpdateCellEntity
import com.baker.eggtart.core.network.mandalart.entities.ResCellEntity
import com.baker.eggtart.core.network.mandalart.entities.ResCellTodosEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/18
 **/
class MandalartRemoteSourceImpl @Inject constructor(@KtorTokenClient private val ktorTokenClient: HttpClient) : MandalartRemoteSource {
    private val sheet = "/sheet"
    private val getCells = "/sheet/{sheet_id}/cell"
    private val cell = "/cell/{cell_id}"

    override suspend fun getSheets(): List<Long> = ktorTokenClient.get(sheet).body()

    override suspend fun postCreateSheet(sheetName: String): Long = ktorTokenClient.post(sheet) {
        setBody(ReqCreateSheetEntity(sheetName))
    }.body()

    override suspend fun getCells(sheetId: Long, depth: Int, parentOrder: Int): List<ResCellEntity> = ktorTokenClient.get(getCells.replace("{sheet_id}", sheetId.toString())) {
        parameter("depth", depth)
        parameter("parent_order", parentOrder)
    }.body()

    override suspend fun patchCell(cellId: Long, body: ReqUpdateCellEntity): ResCellTodosEntity = ktorTokenClient.patch(cell.replace("{cell_id}", cellId.toString())) {
        setBody(body)
    }.body()

    override suspend fun deleteCell(cellId: Long): ResCellTodosEntity = ktorTokenClient.delete(cell.replace("{cell_id}", cellId.toString())).body()

    override suspend fun getCellDetail(cellId: Long): ResCellTodosEntity = ktorTokenClient.get(cell.replace("{cell_id}", cellId.toString())).body()
}