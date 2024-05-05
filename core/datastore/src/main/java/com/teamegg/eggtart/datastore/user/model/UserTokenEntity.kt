package com.teamegg.eggtart.datastore.user.model

import kotlinx.serialization.Serializable

/**
 *    Created by 노원진 on 2024/04/30
 **/

@Serializable
data class UserTokenEntity(
    val accessToken: String,
    val refreshToken: String
)