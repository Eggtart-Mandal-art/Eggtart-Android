package com.baker.eggtart.core.network.user.mapper

import com.baker.eggtart.core.network.user.entites.UserInfoEntity
import com.baker.eggtart.core.network.user.entites.UserTokenEntity
import com.baker.eggtart.domain.user.model.UserInfoModel
import com.baker.eggtart.domain.user.model.UserTokenModel

/**
 *    Created by 노원진 on 2024/04/30
 **/

internal fun UserInfoEntity.toUserInfoModel() = UserInfoModel(id, socialProvider, nickname)

internal fun UserTokenEntity.toUserTokenModel() = UserTokenModel(accessToken, refreshToken)