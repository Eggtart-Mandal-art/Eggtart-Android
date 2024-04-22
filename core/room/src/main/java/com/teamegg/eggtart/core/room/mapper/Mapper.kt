package com.teamegg.eggtart.core.room.mapper

import com.teamegg.eggtart.core.room.entity.MandalartCellEntity
import com.teamegg.eggtart.core.room.entity.MandalartSheetEntity
import com.teamegg.eggtart.domain.mandalart.model.CellModel
import com.teamegg.eggtart.domain.mandalart.model.SheetModel

/**
 *  Created by wonjin on 2024/03/28
 **/

fun CellModel.convert(sheetId: Long): MandalartCellEntity = MandalartCellEntity(sheetId, step, order, id, color, goal)

fun MandalartCellEntity.convert(): CellModel = CellModel(step, order, id, color, goal)

fun SheetModel.convert(): MandalartSheetEntity = MandalartSheetEntity(sheetId, ownerId, name, createdAt, modifiedAt, depth1Cell?.convert(sheetId))

fun MandalartSheetEntity.convert(): SheetModel = SheetModel(sheetId, ownerId, name, createdAt, modifiedAt, depth1Cell?.convert())