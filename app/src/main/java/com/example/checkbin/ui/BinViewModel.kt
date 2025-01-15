package com.example.checkbin.ui

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkbin.data.local.BinDataDao
import com.example.checkbin.data.local.toBinData
import com.example.checkbin.data.remote.BinlistApi
import com.example.whatbin.model.BinInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(
    private val binDataDao: BinDataDao,
    private val binlistApi: BinlistApi
) : ViewModel() {

    private val _uiState = MutableStateFlow(BinUiState())
    val uiState: StateFlow<BinUiState> = _uiState.asStateFlow()

    init {
        getHistory()
    }

    fun getBinInfo(binNumber: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val response = binlistApi.getBinInfo(binNumber)
                if (response.isSuccessful) {
                    val result = response.body()
                    _uiState.update { it.copy(binInfo = result, errorMessage = null) }
                    binDataDao.insertBinData(result!!.toBinData())
                    getHistory()
                } else {
                    handleErrorResponse(response.code())
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = "Failed to load data: ${e.message}") }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun handleErrorResponse(code: Int) {
        when (code) {
            404 -> _uiState.update { it.copy(errorMessage = "BIN not found.") }
            429 -> _uiState.update { it.copy(errorMessage = "Too many requests. Please try again later.") }
            else -> _uiState.update { it.copy(errorMessage = "An unknown error occurred.") }
        }
    }

    private fun getHistory() {
        viewModelScope.launch {
            val data = binDataDao.getBinData().first().map { it.toBinInfo() }
            _uiState.update { it.copy(binInfoCardsHistory = data) }
        }
    }

    fun removeErrorMessage() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}

data class BinUiState(
    val errorMessage: String? = null,
    val binInfo: BinInfo? = null,
    val binInfoCardsHistory: List<BinInfo> = listOf(),
    val isLoading: Boolean = false
)