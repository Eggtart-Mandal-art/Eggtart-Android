package com.eggtart.eggtart.domain.usecase.remote

import android.graphics.Color
import com.eggtart.eggtart.domain.model.remote.ResponseMandalartCellModel
import kotlinx.coroutines.coroutineScope

/**
 *  Created by wonjin on 2024/03/29
 **/
class TestGetRemoteMandalartCellsUseCase {
    suspend operator fun invoke(sheetId: Int, depth: Int = 1): List<ResponseMandalartCellModel> = coroutineScope {


        return@coroutineScope listOf(
            ResponseMandalartCellModel(
                step = 2,
                order = 0,
                id = 2,
                color = null,
                goal = null,
            ),
            ResponseMandalartCellModel(
                step = 2,
                order = 1,
                id = 3,
                color = null,
                goal = null,
            ),
            ResponseMandalartCellModel(
                step = 2,
                order = 2,
                id = 4,
                color = null,
                goal = null,
            ),
            ResponseMandalartCellModel(
                step = 2,
                order = 3,
                id = 5,
                color = null,
                goal = null,
            ),
            ResponseMandalartCellModel(
                step = 1,
                order = 0,
                id = 1,
                color = 0xFFD9F273,
                goal = "성공할거야",
            ),
            ResponseMandalartCellModel(
                step = 2,
                order = 4,
                id = 6,
                color = null,
                goal = null,
            ),
            ResponseMandalartCellModel(
                step = 2,
                order = 5,
                id = 7,
                color = null,
                goal = null,
            ),
            ResponseMandalartCellModel(
                step = 2,
                order = 6,
                id = 8,
                color = null,
                goal = null,
            ),
            ResponseMandalartCellModel(
                step = 2,
                order = 7,
                id = 9,
                color = null,
                goal = null,
            ),
        )
    }
}