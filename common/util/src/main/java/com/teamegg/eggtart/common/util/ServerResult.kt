package com.teamegg.eggtart.common.util

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException

/**
 *  Created by wonjin on 2024/04/12
 **/
sealed class ServerResult<T> {
    data class Success<T>(val data: T) : ServerResult<T>()

    data class Failure<T>(val error: ServerErrorModel?) : ServerResult<T>()

    data class Exception<T>(val exception: kotlin.Exception) : ServerResult<T>()

    companion object {
        suspend fun <T> parseException(exception: kotlin.Exception): ServerResult<T> {
            return try {
                when (exception) {
                    is RedirectResponseException -> Failure(exception.response.body<ServerErrorModel>())
                    is ClientRequestException -> Failure(exception.response.body<ServerErrorModel>())
                    is ServerResponseException -> Failure(exception.response.body<ServerErrorModel>())
                    is ResponseException -> Failure(exception.response.body<ServerErrorModel>())
                    else -> Exception(exception)
                }
            } catch (e: kotlin.Exception) {
                Logger.d("response exception: $e")
                Exception(exception)
            }
        }
    }
}