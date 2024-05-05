package com.teamegg.eggtart.domain.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by 노원진 on 2024.04.12
 */

@Serializable
data class UserInfoModel(
    val id: Long,
    val socialProvider: String,
    val nickname: String
)