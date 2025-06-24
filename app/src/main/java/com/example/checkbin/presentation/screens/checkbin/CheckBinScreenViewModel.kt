package com.example.checkbin.presentation.screens.checkbin

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

@HiltViewModel
class CheckBinScreenViewModel @Inject constructor(
    private val binDataRepository: BinDataRepository,
    private val binDataHistoryRepository: BinDataHistoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CheckBinScreenUiState())
    val uiState: StateFlow<CheckBinScreenUiState> = _uiState.asStateFlow()

    fun getBinInfo() {
        viewModelScope.launch {
            _uiState.update { it.copy(loadingState = LoadingState.Loading) }

            val resultData = binDataRepository.getBinInfo(_uiState.value.binInput)

            when (resultData) {
                is Result.Success -> {
                    val mappedData = resultData.data.toBinData()
                    _uiState.update {
                        it.copy(
                            binInfo = mappedData,
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

    fun updateBin(value: String) {
        _uiState.update { it.copy(binInput = value) }
    }

    fun removeErrorMessage() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}