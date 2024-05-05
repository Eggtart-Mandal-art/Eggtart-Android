package com.teamegg.eggtart.core.network.mandalart.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *  Created by wonjin on 2024/04/18
 **/
@Serializable
data class ReqUpdateCellEntity(
    val goal: String?,
    val color: String?,
    @SerialName("is_completed")
    val isCompleted: Boolean,
    val todos: List<String>
)