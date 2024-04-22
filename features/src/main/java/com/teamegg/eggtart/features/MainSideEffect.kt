package com.teamegg.eggtart.features

import com.teamegg.eggtart.domain.mandalart.model.CellModel

/**
 *  Created by wonjin on 2024/03/29
 **/
sealed class MainSideEffect {
    data class NavigateLoginWithKakaoResult(val kakaoAccessToken: Result<String?>) : MainSideEffect()

    data object NavigateLogin : MainSideEffect()

    data class NavigateHome(val sheetsIds: List<Long>) : MainSideEffect()

    data class NavigateWriteGoal(val cellModel: CellModel) : MainSideEffect()
}