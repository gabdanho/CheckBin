package com.example.checkbin.presentation.model

/**
 * Представляет состояние загрузки при выполнении асинхронной операции.
 *
 * Используется, например, для управления UI в зависимости от текущего статуса загрузки.
 */
sealed class LoadingState {

    /**
     * Состояние активной загрузки.
     */
    data object Loading : LoadingState()

    /**
     * Состояние успешного завершения загрузки.
     */
    data object Success : LoadingState()

    /**
     * Состояние ошибки, возникшей во время загрузки.
     *
     * @property message описание ошибки.
     */
    data class Error(val message: String) : LoadingState()
}
