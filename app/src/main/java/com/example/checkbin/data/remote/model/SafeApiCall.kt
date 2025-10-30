package com.example.checkbin.data.remote.model

import com.example.checkbin.domain.model.result.ApiResult
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import okio.IOException
import java.net.SocketTimeoutException

/**
 * Обертка для безопасного выполнения API-запросов с обработкой ошибок.
 *
 * @param apiCall Запрос к API
 * @return Result с данными или ошибкой
 *
 * Обрабатывает:
 * - SocketTimeoutException
 * - IOException
 * - Exception
 */
suspend fun <T> safeApiCall(
    timeoutMillis: Long = 10000,
    apiCall: suspend () -> T,
): ApiResult<T> {
    return try {
        val result = withTimeout(timeoutMillis) {
            apiCall()
        }
        ApiResult.Success(result)
    } catch (_: TimeoutCancellationException) {
        ApiResult.TimeoutError(message = "The server response time has been exceeded")
    } catch (e: SocketTimeoutException) {
        ApiResult.TimeoutError(message = e.toString())
    } catch (e: IOException) {
        ApiResult.ConnectionError(message = e.toString())
    } catch (e: Exception) {
        ApiResult.UnknownError(message = e.toString())
    }
}