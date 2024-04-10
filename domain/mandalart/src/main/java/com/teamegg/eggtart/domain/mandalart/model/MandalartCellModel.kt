package com.teamegg.eggtart.domain.mandalart.model

/**
 *  Created by wonjin on 2024/03/28
 **/
data class MandalartCellModel(
    val step: Int,
    val order: Int,
    val id: Int,
    val color: Long? = null,
    val goal: String? = null
)