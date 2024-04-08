package com.teamegg.eggtart.data.mapper

import com.teamegg.eggtart.data.entity.local.MandalartCellEntity
import com.teamegg.eggtart.data.entity.local.MandalartSheetEntity
import com.teamegg.eggtart.domain.model.local.MandalartCellModel
import com.teamegg.eggtart.domain.model.local.MandalartSheetModel

/**
 *  Created by wonjin on 2024/03/28
 **/

fun MandalartCellModel.convert(): MandalartCellEntity = MandalartCellEntity(sheetId, cellId, color, goal, order, depth, children.map(MandalartCellModel::convert))

fun MandalartCellEntity.convert(): MandalartCellModel = MandalartCellModel(sheetId, cellId, color, goal, order, depth, children.map(MandalartCellEntity::convert))

fun MandalartSheetModel.convert(): MandalartSheetEntity = MandalartSheetEntity(sheetId, ownerId, name, createdAt, modifiedAt, depth1Cell?.convert())

fun MandalartSheetEntity.convert(): MandalartSheetModel = MandalartSheetModel(sheetId, ownerId, name, createdAt, modifiedAt, depth1Cell?.convert())