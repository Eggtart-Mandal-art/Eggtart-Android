package com.teamegg.eggtart.core.network.mandalart.datasource

import com.teamegg.eggtart.common.util.KtorTokenClient
import com.teamegg.eggtart.core.network.mandalart.entities.PatchCellEntity
import com.teamegg.eggtart.core.network.mandalart.entities.RequestCreateSheetEntity
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/18
 **/
class MandalartRemoteSourceImpl @Inject constructor(@KtorTokenClient private val ktorTokenClient: HttpClient) : MandalartRemoteSource {
    private val sheet = "/sheet"
    private val getCells = "/sheet/{sheet_id}/cell"
    private val cell = "/cell/{cell_id}"

    override suspend fun getSheets(accessToken: String): String = ktorTokenClient.get(sheet).bodyAsText()

    override suspend fun postCreateSheet(accessToken: String, sheetName: String): String = ktorTokenClient.post(sheet) {
        setBody(RequestCreateSheetEntity(sheetName))
    }.bodyAsText()

    override suspend fun getCells(accessToken: String, sheetId: Long, depth: Int, parentOrder: Int): String = ktorTokenClient.get(getCells.replace("{sheet_id}", sheetId.toString())) {
        parameter("depth", depth)
        parameter("parent_order", parentOrder)
    }.bodyAsText()

    override suspend fun patchCell(accessToken: String, cellId: Long, body: PatchCellEntity): String = ktorTokenClient.patch(cell.replace("{cell_id}", cellId.toString())) {
        setBody(body)
    }.bodyAsText()

    override suspend fun deleteCell(accessToken: String, cellId: Long): String = ktorTokenClient.delete(cell.replace("{cell_id}", cellId.toString())).bodyAsText()

    override suspend fun getCellDetail(accessToken: String, cellId: Long): String = ktorTokenClient.get(cell.replace("{cell_id}", cellId.toString())).bodyAsText()
}