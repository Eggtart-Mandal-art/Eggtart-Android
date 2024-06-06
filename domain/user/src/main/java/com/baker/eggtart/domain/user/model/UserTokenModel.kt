package com.baker.eggtart.domain.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *  Created by wonjin on 2024/04/12
 **/

@Serializable
data class UserTokenModel(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String
)