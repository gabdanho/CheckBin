package com.example.checkbin.data.remote

import com.example.checkbin.domain.model.Result
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * Обертка для безопасного выполнения API-запросов с обработкой ошибок.
 *
 * @param apiCall Запрос к API
 * @return Result с данными или ошибкой
 *
 * Обрабатывает:
 * - HttpException
 * - SocketTimeoutException
 * - IOException
 * - Exception
 */
suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        Result.Success(apiCall())
    } catch (e: HttpException) {
        Result.ServerError(
            message = e.toString(),
            errorCode = e.code()
        )
    } catch (e: SocketTimeoutException) {
        Result.TimeoutError(message = e.toString())
    } catch (e: IOException) {
        Result.ConnectionError(message = e.toString())
    } catch (e: Exception) {
        Result.UnknownError(message = e.toString())
    }
}