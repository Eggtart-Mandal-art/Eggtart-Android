package com.teamegg.eggtart.core.network.mandalart.mapper

import com.teamegg.eggtart.core.network.mandalart.entities.PatchCellEntity
import com.teamegg.eggtart.core.network.mandalart.entities.TodoEntity
import com.teamegg.eggtart.domain.mandalart.model.TodoModel
import com.teamegg.eggtart.domain.mandalart.model.UpdateCellModel

/**
 *  Created by wonjin on 2024/04/18
 **/

internal fun PatchCellEntity.toUpdateCellModel(): UpdateCellModel = UpdateCellModel(
    goal = goal,
    color = color,
    isCompleted = isCompleted,
    todos = todos
)

internal fun UpdateCellModel.toPatchCellEntity(): PatchCellEntity = PatchCellEntity(
    goal = goal,
    color = color,
    isCompleted = isCompleted,
    todos = todos
)

internal fun TodoModel.toTodoEntity(): TodoEntity = TodoEntity(
    id = id,
    content = content,
    cellId = cellId
)

internal fun TodoEntity.toTodoModel(): TodoModel = TodoModel(
    id = id,
    content = content,
    cellId = cellId
)