package com.example.checkbin.data.remote.model

import com.example.checkbin.domain.model.result.ApiResult
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
suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResult<T> {
    return try {
        ApiResult.Success(apiCall())
    } catch (e: HttpException) {
        ApiResult.ServerError(
            message = e.toString(),
            errorCode = e.code()
        )
    } catch (e: SocketTimeoutException) {
        ApiResult.TimeoutError(message = e.toString())
    } catch (e: IOException) {
        ApiResult.ConnectionError(message = e.toString())
    } catch (e: Exception) {
        ApiResult.UnknownError(message = e.toString())
    }
}