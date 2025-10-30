package com.example.checkbin.presentation.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import com.example.checkbin.domain.model.result.DbResult
import com.example.checkbin.presentation.mapper.BinDataMapper.toPresentation
import com.example.checkbin.presentation.model.LoadingState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel для экрана истории проверок BIN.
 *
 * Загружает историю запросов BIN из репозитория и предоставляет
 * состояние UI для отображения этих данных.
 *
 * @property binDataHistoryRepository репозиторий для получения истории данных BIN.
 */
class HistoryBinDataScreenViewModel(
    private val binDataHistoryRepository: BinDataHistoryRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryBinDataScreenUiState())
    val uiState: StateFlow<HistoryBinDataScreenUiState> = _uiState.asStateFlow()

    init {
        getHistoryInfo()
    }

    /**
     * Загружает историю данных BIN из репозитория и обновляет состояние UI.
     */
    private fun getHistoryInfo() {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(loadingState = LoadingState.Loading) }

            when (val resultData = binDataHistoryRepository.getBinDataHistory()) {
                is DbResult.Success -> {
                    val mappedData = resultData.data.map { it.toPresentation() }
                    _uiState.update {
                        it.copy(
                            binHistory = mappedData,
                            loadingState = LoadingState.Success
                        )
                    }
                }

                is DbResult.Error -> {
                    _uiState.update {
                        it.copy(
                            loadingState = LoadingState.Error(
                                message = resultData.message
                            )
                        )
                    }
                }
            }
        }
    }
}