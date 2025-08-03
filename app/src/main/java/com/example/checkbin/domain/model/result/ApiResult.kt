package com.example.checkbin.domain.model.result

/**
 * Обобщённый класс-обёртка, представляющий результат выполнения API операций.
 *
 * Может содержать успешный результат или один из возможных типов ошибок.
 *
 * @param T тип данных в случае успешного выполнения.
 */
sealed class ApiResult<out T> {

    /**
     * Представляет успешный результат операции.
     *
     * @param T тип возвращаемых данных.
     * @property data данные, полученные в результате успешной операции.
     */
    data class Success<out T>(val data: T) : ApiResult<T>()

    /**
     * Ошибка, возникшая на стороне сервера (например, 5xx или 4xx HTTP-коды).
     *
     * @property message сообщение об ошибке.
     * @property errorCode необязательный код ошибки (например, HTTP-статус).
     */
    data class ServerError(
        val message: String,
        val errorCode: Int? = null
    ) : ApiResult<Nothing>()

    /**
     * Ошибка, связанная с превышением времени ожидания (тайм-аут).
     *
     * @property message сообщение об ошибке.
     */
    data class TimeoutError(val message: String) : ApiResult<Nothing>()

    /**
     * Ошибка подключения к сети или удалённому серверу.
     *
     * @property message сообщение об ошибке.
     */
    data class ConnectionError(val message: String) : ApiResult<Nothing>()

    /**
     * Неизвестная ошибка, не подпадающая под другие категории.
     *
     * @property message сообщение об ошибке.
     */
    data class UnknownError(val message: String) : ApiResult<Nothing>()
}
