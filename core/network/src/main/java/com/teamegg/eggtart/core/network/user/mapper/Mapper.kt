package com.teamegg.eggtart.core.network.user.mapper

import com.teamegg.eggtart.core.network.user.entites.UserInfoEntity
import com.teamegg.eggtart.core.network.user.entites.UserTokenEntity
import com.teamegg.eggtart.domain.user.model.UserInfoModel
import com.teamegg.eggtart.domain.user.model.UserTokenModel

/**
 *    Created by 노원진 on 2024/04/30
 **/

internal fun UserInfoEntity.toUserInfoModel() = UserInfoModel(id, socialProvider, nickname)

internal fun UserTokenEntity.toUserTokenModel() = UserTokenModel(accessToken, refreshToken)