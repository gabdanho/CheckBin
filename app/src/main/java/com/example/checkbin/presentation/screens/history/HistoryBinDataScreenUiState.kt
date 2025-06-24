package com.example.checkbin.presentation.screens.history

import com.example.checkbin.presentation.model.BinData

/**
 * Представляет состояние UI экрана истории проверок BIN.
 *
 * @property binHistory список данных BIN, отображаемых в истории.
 */
data class HistoryBinDataScreenUiState(
    val binHistory: List<BinData>? = null
)