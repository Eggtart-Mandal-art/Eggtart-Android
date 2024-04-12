package com.teamegg.eggtart.core.network.user.mapper

import com.teamegg.eggtart.core.network.user.model.UserTokenEntity
import com.teamegg.eggtart.domain.user.model.UserTokenModel

/**
 *  Created by wonjin on 2024/04/12
 **/

internal fun UserTokenEntity.toUserTokenModel() = UserTokenModel(accessToken, refreshToken)

internal fun UserTokenModel.toUserTokenEntity() = UserTokenEntity(accessToken, refreshToken)