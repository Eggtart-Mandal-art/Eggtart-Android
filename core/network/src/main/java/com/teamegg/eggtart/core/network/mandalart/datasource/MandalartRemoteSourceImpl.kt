package com.teamegg.eggtart.core.network.mandalart.datasource

import com.teamegg.eggtart.core.network.mandalart.entities.PatchCellEntity
import com.teamegg.eggtart.core.network.mandalart.entities.RequestCreateSheetEntity
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.parameters
import javax.inject.Inject

/**
 *  Created by wonjin on 2024/04/18
 **/
class MandalartRemoteSourceImpl @Inject constructor(private val ktorClient: HttpClient) : MandalartRemoteSource {
    private val sheet = "/sheet"
    private val getCells = "/sheet/{sheet_id}/cell"
    private val patchCell = "/cell/{cell_id}"

    override suspend fun getSheets(accessToken: String): String = ktorClient.get(sheet) {
        bearerAuth(accessToken)
    }.bodyAsText()

    override suspend fun postCreateSheet(accessToken: String, sheetName: String): String = ktorClient.post(sheet) {
        bearerAuth(accessToken)
        setBody(RequestCreateSheetEntity(sheetName))
    }.bodyAsText()

    override suspend fun getCells(accessToken: String, sheetId: Long, depth: Int, parentOrder: Int): String = ktorClient.get(getCells.replace("{sheet_id}", sheetId.toString())) {
        bearerAuth(accessToken)
        parameters {
            append("depth", depth.toString())
            append("parent_order", parentOrder.toString())
        }
    }.bodyAsText()

    override suspend fun patchCell(accessToken: String, cellId: Long, body: PatchCellEntity): String = ktorClient.patch(patchCell.replace("{cell_id}", cellId.toString())) {
        bearerAuth(accessToken)
        setBody(body)
    }.bodyAsText()
}