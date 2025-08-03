package com.example.checkbin.presentation.screens.checkbin

import com.example.checkbin.presentation.model.BinData
import com.example.checkbin.presentation.model.LoadingState

/**
 * Представляет состояние UI для экрана проверки BIN.
 *
 * @property errorMessage текст сообщения об ошибке.
 * @property binData информация о BIN-карте, полученная после успешного запроса.
 * @property binInput текущий ввод пользователя в поле для BIN.
 * @property loadingState текущее состояние загрузки, например, [LoadingState.Loading], [LoadingState.Success] или [LoadingState.Error].
 */
data class CheckBinScreenUiState(
    val errorMessage: String? = null,
    val binData: BinData? = null,
    val binInput: String = "",
    val isButtonEnabled: Boolean = false,
    val loadingState: LoadingState? = null
)