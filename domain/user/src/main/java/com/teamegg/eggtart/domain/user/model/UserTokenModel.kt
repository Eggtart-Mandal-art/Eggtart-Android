package com.teamegg.eggtart.domain.user.model

import kotlinx.serialization.Serializable

/**
 *  Created by wonjin on 2024/04/12
 **/

@Serializable
data class UserTokenModel(
    val accessToken: String,
    val refreshToken: String
)