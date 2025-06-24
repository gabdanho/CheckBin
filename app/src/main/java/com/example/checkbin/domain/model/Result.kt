package com.example.checkbin.domain.model

sealed class Result<out T> {

    data class Success<out T>(val data: T): Result<T>()

    data class ServerError(
        val message: String,
        val errorCode: Int? = null
    ): Result<Nothing>()

    data class TimeoutError(val message: String): Result<Nothing>()

    data class ConnectionError(val message: String): Result<Nothing>()

    data class UnknownError(val message: String): Result<Nothing>()
}