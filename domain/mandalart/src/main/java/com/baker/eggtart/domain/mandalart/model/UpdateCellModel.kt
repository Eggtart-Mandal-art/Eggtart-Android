package com.baker.eggtart.domain.mandalart.model

import kotlinx.serialization.Serializable

/**
 *    Created by 노원진 on 2024/04/23
 **/

@Serializable
data class UpdateCellModel(
    val color: String? = null,
    val goal: String? = null,
    val isCompleted: Boolean = false,
    val todos: List<String> = listOf()
)