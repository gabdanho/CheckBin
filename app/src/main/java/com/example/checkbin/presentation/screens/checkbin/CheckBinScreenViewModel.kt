package com.example.checkbin.presentation.screens.checkbin

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import com.example.checkbin.domain.interfaces.repository.BinDataRepository
import com.example.checkbin.domain.model.Result
import com.example.checkbin.presentation.mapper.BinDataMapper.toBinData
import com.example.checkbin.presentation.model.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для экрана проверки BIN.
 *
 * Управляет состоянием UI, обрабатывает запросы к репозиторию данных о BIN,
 * а также сохраняет успешные результаты в историю.
 *
 * @property binDataRepository репозиторий для получения данных о BIN.
 * @property binDataHistoryRepository репозиторий для сохранения истории запросов BIN.
 */
@HiltViewModel
class CheckBinScreenViewModel @Inject constructor(
    private val binDataRepository: BinDataRepository,
    private val binDataHistoryRepository: BinDataHistoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CheckBinScreenUiState())
    val uiState: StateFlow<CheckBinScreenUiState> = _uiState.asStateFlow()

    /**
     * Запускает асинхронный запрос информации по введённому BIN.
     */
    fun getBinInfo() {
        viewModelScope.launch {
            _uiState.update { it.copy(loadingState = LoadingState.Loading) }

            when (val resultData = binDataRepository.getBinInfo(_uiState.value.binInput)) {
                is Result.Success -> {
                    val mappedData = resultData.data.toBinData()
                    _uiState.update {
                        it.copy(
                            binData = mappedData,
                            loadingState = LoadingState.Success
                        )
                    }
                    binDataHistoryRepository.insertBinDataInHistory(resultData.data)
                }

                is Result.ConnectionError -> _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Error(
                            message = resultData.message
                        )
                    )
                }

                is Result.ServerError -> _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Error(
                            message = resultData.message
                        )
                    )
                }

                is Result.TimeoutError -> _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Error(
                            message = resultData.message
                        )
                    )
                }

                is Result.UnknownError -> _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Error(
                            message = resultData.message
                        )
                    )
                }
            }
        }
    }

    /**
     * Обновляет ввод пользователя для поля BIN.
     *
     * @param value новое значение ввода.
     */
    fun updateBin(value: String) {
        if (value.isDigitsOnly()) {
            _uiState.update { it.copy(binInput = value) }
        }
    }

    /**
     * Очищает состояние ошибок и загрузки.
     *
     * Вызывается после отображения ошибки, чтобы сбросить UI в нейтральное состояние.
     */
    fun removeErrorState() {
        _uiState.update {
            it.copy(
                errorMessage = null,
                loadingState = null
            )
        }
    }
}