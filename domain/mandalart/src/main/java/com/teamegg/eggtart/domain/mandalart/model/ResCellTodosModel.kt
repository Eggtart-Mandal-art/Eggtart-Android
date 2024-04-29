package com.teamegg.eggtart.domain.mandalart.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *    Created by 노원진 on 2024/04/23
 **/

@Serializable
data class ResCellTodosModel(
    val step: Int,
    val id: Long,
    val color: String? = null,
    val goal: String? = null,
    @SerialName("is_completed")
    val isCompleted: Boolean = false,
    val todos: List<TodoModel> = listOf()
)