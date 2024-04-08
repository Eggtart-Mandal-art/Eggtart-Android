package com.teamegg.eggtart.domain.model.remote

/**
 *  Created by wonjin on 2024/03/29
 **/
data class ResponseMandalartCellModel(
    val step: Int,
    val order: Int,
    val id: Int,
    val color: Long? = null,
    val goal: String? = null
)