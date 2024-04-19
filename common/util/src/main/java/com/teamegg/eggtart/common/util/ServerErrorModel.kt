package com.teamegg.eggtart.common.util

import kotlinx.serialization.Serializable

/**
 *  Created by wonjin on 2024/04/12
 **/

@Serializable
data class ServerErrorModel(
    val status: Int,
    val message: String
)