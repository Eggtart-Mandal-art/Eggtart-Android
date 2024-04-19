package com.teamegg.eggtart.common.util

/**
 *  Created by wonjin on 2024/04/12
 **/
sealed class Result<T> {
    data class Success<T>(val data: T): Result<T>()

    data class Failure<T>(val error: ServerErrorModel?): Result<T>()
}