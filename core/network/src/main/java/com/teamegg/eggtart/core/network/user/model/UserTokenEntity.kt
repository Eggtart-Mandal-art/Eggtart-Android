package com.teamegg.eggtart.core.network.user.model

import kotlinx.serialization.Serializable

/**
 *  Created by wonjin on 2024/04/12
 **/

@Serializable
data class UserTokenEntity(
    val accessToken: String,
    val refreshToken: String
)