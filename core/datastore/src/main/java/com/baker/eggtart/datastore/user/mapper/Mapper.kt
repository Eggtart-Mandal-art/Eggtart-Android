package com.baker.eggtart.datastore.user.mapper

import com.baker.eggtart.datastore.user.model.UserInfoEntity
import com.baker.eggtart.datastore.user.model.UserTokenEntity
import com.baker.eggtart.domain.user.model.UserInfoModel
import com.baker.eggtart.domain.user.model.UserTokenModel

/**
 *    Created by 노원진 on 2024/04/30
 **/

internal fun UserInfoEntity.toUserInfoModel() = UserInfoModel(id, socialProvider, nickname)

internal fun UserInfoModel.toUserInfoEntity() = UserInfoEntity(id, socialProvider, nickname)

internal fun UserTokenEntity.toUserTokenModel() = UserTokenModel(accessToken, refreshToken)

internal fun UserTokenModel.toUserTokenEntity() = UserTokenEntity(accessToken, refreshToken)