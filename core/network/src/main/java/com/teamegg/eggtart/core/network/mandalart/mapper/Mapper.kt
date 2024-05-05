package com.teamegg.eggtart.core.network.mandalart.mapper

import com.teamegg.eggtart.core.network.mandalart.entities.ReqUpdateCellEntity
import com.teamegg.eggtart.core.network.mandalart.entities.ResCellEntity
import com.teamegg.eggtart.core.network.mandalart.entities.ResCellTodosEntity
import com.teamegg.eggtart.core.network.mandalart.entities.TodoEntity
import com.teamegg.eggtart.domain.mandalart.model.CellModel
import com.teamegg.eggtart.domain.mandalart.model.CellTodosModel
import com.teamegg.eggtart.domain.mandalart.model.TodoModel
import com.teamegg.eggtart.domain.mandalart.model.UpdateCellModel

/**
 *  Created by wonjin on 2024/04/18
 **/

internal fun ResCellEntity.toCellModel(): CellModel = CellModel(step, id, color, goal, isCompleted)

internal fun ResCellTodosEntity.toCellTodosModel(): CellTodosModel = CellTodosModel(step, id, color, goal, isCompleted, todos.map(TodoEntity::toTodoModel))

internal fun UpdateCellModel.toPatchCellEntity(): ReqUpdateCellEntity = ReqUpdateCellEntity(
    goal = goal,
    color = color,
    isCompleted = isCompleted,
    todos = todos
)

internal fun TodoEntity.toTodoModel(): TodoModel = TodoModel(
    id = id,
    content = content,
    cellId = cellId
)