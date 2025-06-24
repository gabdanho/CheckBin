package com.example.checkbin.presentation.model

sealed class LoadingState {
    data object Loading : LoadingState()
    data object Success: LoadingState()
    data class Error(val message: String): LoadingState()
}