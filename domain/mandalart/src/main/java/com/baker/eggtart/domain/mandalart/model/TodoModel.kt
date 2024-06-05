package com.baker.eggtart.domain.mandalart.model

import kotlinx.serialization.Serializable

@Serializable
data class TodoModel(
    val content: String = "",
    val id: Long = 0,
    val cellId: Long = 0
)