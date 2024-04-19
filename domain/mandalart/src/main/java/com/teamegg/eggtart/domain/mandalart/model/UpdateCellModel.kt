package com.teamegg.eggtart.domain.mandalart.model

import kotlinx.serialization.Serializable

/**
 *  Created by wonjin on 2024/04/18
 **/

@Serializable
data class UpdateCellModel(
    val goal: String,
    val color: Long,
    val isCompleted: Boolean
)