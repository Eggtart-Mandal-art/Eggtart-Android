package com.teamegg.eggtart.domain.mandalart.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoModel(
    val content: String = "",
    val id: Long = 0,
    @SerialName("cell_id")
    val cellId: Long = 0
)