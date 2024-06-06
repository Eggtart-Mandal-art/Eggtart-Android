package com.baker.eggtart.core.network.mandalart.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *    Created by 노원진 on 2024/04/23
 **/

@Serializable
data class TodoEntity(
    val content: String,
    val id: Long,
    @SerialName("cell_id")
    val cellId: Long
)