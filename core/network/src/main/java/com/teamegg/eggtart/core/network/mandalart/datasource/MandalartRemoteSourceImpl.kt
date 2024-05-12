package com.teamegg.eggtart.core.network.mandalart.datasource

import com.teamegg.eggtart.common.util.KtorTokenClient
import com.teamegg.eggtart.core.network.mandalart.entities.ReqCreateSheetEntity
import com.teamegg.eggtart.core.network.mandalart.entities.ReqUpdateCellEntity
import com.teamegg.eggtart.core.network.mandalart.entities.ResCellEntity
import com.teamegg.eggtart.core.network.mandalart.entities.ResCellTodosEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/18
 **/
class MandalartRemoteSourceImpl @Inject constructor(@KtorTokenClient private val ktorTokenClient: HttpClient) : MandalartRemoteSource {
    private val sheet = "/sheet"
    private val getCells = "/sheet/{sheet_id}/cell/main"
    private val cell = "/cell/{cell_id}"
    private val cellChildren = "/cell/{cell_id}/children"

    override suspend fun getSheets(): List<Long> = ktorTokenClient.get(sheet).body()

    override suspend fun postCreateSheet(sheetName: String): Long = ktorTokenClient.post(sheet) {
        setBody(ReqCreateSheetEntity(sheetName))
    }.body()

    override suspend fun getCells(sheetId: Long): List<ResCellEntity> = ktorTokenClient.get(getCells.replace("{sheet_id}", sheetId.toString())).body()

    override suspend fun patchCell(cellId: Long, body: ReqUpdateCellEntity): ResCellTodosEntity = ktorTokenClient.patch(cell.replace("{cell_id}", cellId.toString())) {
        setBody(body)
    }.body()

    override suspend fun deleteCell(cellId: Long): ResCellTodosEntity = ktorTokenClient.delete(cell.replace("{cell_id}", cellId.toString())).body()

    override suspend fun getCellDetail(cellId: Long): ResCellTodosEntity = ktorTokenClient.get(cell.replace("{cell_id}", cellId.toString())).body()

    override suspend fun getCellChildren(cellId: Long): List<ResCellEntity> = ktorTokenClient.get(cellChildren.replace("{cell_id}", cellId.toString())).body()
}