package com.teamegg.eggtart.features

/**
 *  Created by wonjin on 2024/03/29
 **/

data class MainState(
    val initialized: Boolean = false,
    val sheetIds: List<Long> = listOf()
)