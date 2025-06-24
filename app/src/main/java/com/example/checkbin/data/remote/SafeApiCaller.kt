package com.example.checkbin.data.remote

import com.example.checkbin.domain.model.Result
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        Result.Success(apiCall())
    } catch (e: HttpException) {
        Result.ServerError(
            message = "fun safeApiCall ServerError: $e",
            errorCode = e.code()
        )
    } catch (e: SocketTimeoutException) {
        Result.TimeoutError(message = "fun safeApiCall TimeoutError: $e")
    } catch (e: IOException) {
        Result.ConnectionError(message = "fun safeApiCall ConnectionError: $e")
    } catch (e: Exception) {
        Result.UnknownError(message = "fun safeApiCall UnknownError: $e")
    }
}