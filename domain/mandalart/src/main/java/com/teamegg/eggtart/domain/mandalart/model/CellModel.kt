package com.teamegg.eggtart.domain.mandalart.model

import kotlinx.serialization.Serializable

/**
 *  Created by wonjin on 2024/03/28
 **/

@Serializable
data class CellModel(
    val step: Int,
    val order: Int,
    val id: Int,
    val color: Long? = null,
    val goal: String? = null
)