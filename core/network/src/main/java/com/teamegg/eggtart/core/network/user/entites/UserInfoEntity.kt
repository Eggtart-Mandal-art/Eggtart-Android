package com.teamegg.eggtart.core.network.user.entites

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by 노원진 on 2024.04.12
 */

@Serializable
data class UserInfoEntity(
    val id: Long,
    @SerialName("social_provider")
    val socialProvider: String,
    val nickname: String
)