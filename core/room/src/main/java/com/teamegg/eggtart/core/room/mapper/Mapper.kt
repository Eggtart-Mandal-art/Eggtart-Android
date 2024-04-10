package com.teamegg.eggtart.core.room.mapper

import com.teamegg.eggtart.core.room.entity.MandalartCellEntity
import com.teamegg.eggtart.core.room.entity.MandalartSheetEntity
import com.teamegg.eggtart.domain.mandalart.model.MandalartCellModel
import com.teamegg.eggtart.domain.mandalart.model.MandalartSheetModel

/**
 *  Created by wonjin on 2024/03/28
 **/

fun MandalartCellModel.convert(sheetId: Long): MandalartCellEntity = MandalartCellEntity(sheetId, step, order, id, color, goal)

fun MandalartCellEntity.convert(): MandalartCellModel = MandalartCellModel(step, order, id, color, goal)

fun MandalartSheetModel.convert(): MandalartSheetEntity = MandalartSheetEntity(sheetId, ownerId, name, createdAt, modifiedAt, depth1Cell?.convert(sheetId))

fun MandalartSheetEntity.convert(): MandalartSheetModel = MandalartSheetModel(sheetId, ownerId, name, createdAt, modifiedAt, depth1Cell?.convert())