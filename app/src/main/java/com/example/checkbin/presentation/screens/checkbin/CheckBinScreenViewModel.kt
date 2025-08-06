package com.example.checkbin.presentation.screens.checkbin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkbin.di.IoDispatcher
import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import com.example.checkbin.domain.interfaces.repository.BinDataRepository
import com.example.checkbin.domain.model.result.ApiResult
import com.example.checkbin.presentation.BIN_LENGTH
import com.example.checkbin.presentation.mapper.BinDataMapper.toPresentation
import com.example.checkbin.presentation.model.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
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
    private val binDataHistoryRepository: BinDataHistoryRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(CheckBinScreenUiState())
    val uiState: StateFlow<CheckBinScreenUiState> = _uiState.asStateFlow()

    /**
     * Запускает асинхронный запрос информации по введённому BIN.
     */
    fun getBinInfo() {
        viewModelScope.launch(dispatcher) {
            _uiState.update {
                it.copy(
                    loadingState = LoadingState.Loading,
                    isButtonEnabled = false
                )
            }
            when (val resultData = binDataRepository.getBinInfo(_uiState.value.binInput)) {
                is ApiResult.Success -> {
                    val mappedData = resultData.data.toPresentation()
                    _uiState.update {
                        it.copy(
                            binData = mappedData,
                            loadingState = LoadingState.Success,
                            isButtonEnabled = true
                        )
                    }
                    binDataHistoryRepository.insertBinDataInHistory(resultData.data)
                }

                is ApiResult.ConnectionError -> _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Error(
                            message = resultData.message
                        ),
                        isButtonEnabled = true
                    )
                }

                is ApiResult.ServerError -> _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Error(
                            message = resultData.message
                        ),
                        isButtonEnabled = true
                    )
                }

                is ApiResult.TimeoutError -> _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Error(
                            message = resultData.message
                        ),
                        isButtonEnabled = true
                    )
                }

                is ApiResult.UnknownError -> _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Error(
                            message = resultData.message
                        ),
                        isButtonEnabled = true
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
        if (value.all { it.isDigit() } && value.length <= BIN_LENGTH) {
            _uiState.update {
                it.copy(
                    binInput = value,
                    isButtonEnabled = value.length == BIN_LENGTH
                )
            }
        }
    }

    /**
     * Очищает состояние ошибок и загрузки.
     *
     * Вызывается после отображения ошибки, чтобы сбросить UI в нейтральное состояние.
     */
    fun resetLoadingState() {
        _uiState.update { it.copy(loadingState = null) }
    }
}