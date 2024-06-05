package com.baker.eggtart.features

import com.baker.eggtart.common.util.ServerResult
import com.baker.eggtart.domain.mandalart.model.CellModel
import com.baker.eggtart.domain.mandalart.model.CellTodosModel

/**
 *  Created by wonjin on 2024/03/29
 **/
sealed class MainSideEffect {
    data class NavigateLoginWithKakaoResult(val kakaoAccessToken: Result<String?>) : MainSideEffect()

    data object NavigateLogin : MainSideEffect()

    data class NavigateHome(val sheetsIds: List<Long>, val cellModel: CellTodosModel? = null) : MainSideEffect()

    data class NavigateWriteGoal(val cellModel: CellModel) : MainSideEffect()

    data class ServerErrorPopup(val type: ServerCallType, val serverResult: ServerResult<*>): MainSideEffect()
}

enum class ServerCallType {
    CREATE_MANDALART_SHEET,
    GET_MANDALART_SHEET
}