package com.example.checkbin.presentation.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана истории проверок BIN.
 *
 * Загружает историю запросов BIN из репозитория и предоставляет
 * состояние UI для отображения этих данных.
 *
 * @property binDataHistoryRepository репозиторий для получения истории данных BIN.
 */
@HiltViewModel
class HistoryBinDataScreenViewModel @Inject constructor(
    private val binDataHistoryRepository: BinDataHistoryRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(HistoryBinDataScreenUiState())
    val uiState: StateFlow<HistoryBinDataScreenUiState> = _uiState.asStateFlow()


    init {
        getHistoryInfo()
    }

    /**
     * Загружает историю данных BIN из репозитория и обновляет состояние UI.
     */
    private fun getHistoryInfo() {
        viewModelScope.launch {
            _uiState.update { it.copy(binHistory = binDataHistoryRepository.getBinDataHistory()) }
        }
    }
}