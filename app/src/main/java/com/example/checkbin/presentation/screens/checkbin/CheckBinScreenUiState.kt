package com.example.checkbin.presentation.screens.checkbin

import com.example.checkbin.presentation.model.BinData
import com.example.checkbin.presentation.model.LoadingState

data class CheckBinScreenUiState(
    val errorMessage: String? = null,
    val binInfo: BinData? = null,
    val binInput: String = "",
    val loadingState: LoadingState? = null
)