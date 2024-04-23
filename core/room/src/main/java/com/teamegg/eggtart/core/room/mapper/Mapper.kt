package com.teamegg.eggtart.core.room.mapper

import com.teamegg.eggtart.core.room.entity.MandalartCellEntity
import com.teamegg.eggtart.core.room.entity.MandalartSheetEntity
import com.teamegg.eggtart.domain.mandalart.model.ResCellModel
import com.teamegg.eggtart.domain.mandalart.model.SheetModel

/**
 *  Created by wonjin on 2024/03/28
 **/

fun ResCellModel.convert(sheetId: Long): MandalartCellEntity = MandalartCellEntity(sheetId, step, -1, id.toInt(), color?.toLong(16), goal)

fun MandalartCellEntity.convert(): ResCellModel = ResCellModel(step, id.toLong(), color?.toString(), goal)

fun SheetModel.convert(): MandalartSheetEntity = MandalartSheetEntity(sheetId, ownerId, name, createdAt, modifiedAt, depth1Cell?.convert(sheetId))

fun MandalartSheetEntity.convert(): SheetModel = SheetModel(sheetId, ownerId, name, createdAt, modifiedAt, depth1Cell?.convert())