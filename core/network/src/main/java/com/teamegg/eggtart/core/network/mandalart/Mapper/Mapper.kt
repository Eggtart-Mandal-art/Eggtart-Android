package com.teamegg.eggtart.core.network.mandalart.mapper

import com.teamegg.eggtart.core.network.mandalart.entities.PatchCellEntity
import com.teamegg.eggtart.domain.mandalart.model.UpdateCellModel

/**
 *  Created by wonjin on 2024/04/18
 **/

internal fun PatchCellEntity.toUpdateCellModel(): UpdateCellModel = UpdateCellModel(
    goal = goal,
    color = color.toLong(16),
    isCompleted = isCompleted
)

internal fun UpdateCellModel.toPatchCellEntity(): PatchCellEntity = PatchCellEntity(
    goal = goal,
    color = color.toString(16),
    isCompleted = isCompleted
)