package com.example.presentation.screens.checkbin

import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import com.example.checkbin.domain.interfaces.repository.BinDataRepository
import com.example.checkbin.domain.model.result.ApiResult
import com.example.checkbin.presentation.mapper.BinDataMapper.toPresentation
import com.example.checkbin.presentation.model.bin.BinData
import com.example.checkbin.presentation.model.LoadingState
import com.example.checkbin.presentation.screens.checkbin.CheckBinScreenUiState
import com.example.checkbin.presentation.screens.checkbin.CheckBinScreenViewModel
import com.example.fake.BinDataFake.binDataDomainFake
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CheckBinScreenViewModelTest {

    private lateinit var viewModel: CheckBinScreenViewModel
    private lateinit var binDataRepository: BinDataRepository
    private lateinit var binDataHistoryRepository: BinDataHistoryRepository
    private val testDispatcher: TestDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        binDataRepository = mockk()
        binDataHistoryRepository = mockk()

        viewModel = CheckBinScreenViewModel(
            binDataRepository = binDataRepository,
            binDataHistoryRepository = binDataHistoryRepository,
            dispatcher = testDispatcher
        )
    }

    @After
    fun clear() {
        Dispatchers.resetMain()
    }

    // START | test updateBin() area

    @Test
    fun `updateBin updates binInput and isButtonEnabled when bin is correct`() {
        val validBin = "220070"

        viewModel.updateBin(validBin)

        assertEquals(validBin, viewModel.uiState.value.binInput)
        assertTrue(viewModel.uiState.value.isButtonEnabled)
    }

    @Test
    fun `updateBin updates binInput and isButtonEnabled equals false when bin is not 6 symbols`() {
        val validBin = "22007"

        viewModel.updateBin(validBin)

        assertEquals(validBin, viewModel.uiState.value.binInput)
        assertFalse(viewModel.uiState.value.isButtonEnabled)
    }

    @Test
    fun `updateBin updates should not update binInput and isButtonEnabled when bin is not number`() {
        val validBin = "invalid"

        viewModel.updateBin(validBin)

        assertEquals("", viewModel.uiState.value.binInput)
        assertFalse(viewModel.uiState.value.isButtonEnabled)
    }

    // END | test updateBin() area

    // test resetLoadingState()

    @Test
    fun `resetLoadingState updates errorMessage and loadingState to null`() {
        viewModel.updateUiStateForTest(loadingState = LoadingState.Error("Unknown error"))

        viewModel.resetLoadingState()

        assertEquals(null, viewModel.uiState.value.loadingState)
    }

    // START : test getBinInfo() area

    @Test
    fun `getBinInfo successfully receives data and updates uiState correctly`() = runTest {
        val binRequest = "220070"
        val binDataResponse = binDataDomainFake

        coEvery { binDataRepository.getBinInfo(binRequest) } returns ApiResult.Success(binDataResponse)
        coEvery { binDataHistoryRepository.insertBinDataInHistory(binDataResponse) } just Runs

        viewModel.updateUiStateForTest(binInput = binRequest)
        viewModel.getBinInfo()

        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { binDataHistoryRepository.insertBinDataInHistory(binDataResponse) }
        assertEquals(LoadingState.Success, viewModel.uiState.value.loadingState)
        assertEquals(binDataResponse.toPresentation(), viewModel.uiState.value.binData)
    }

    @Test
    fun `getBinInfo returns an error when called and the error is displayed in uiState`() = runTest {
        val binRequest = "220070"

        coEvery { binDataRepository.getBinInfo(binRequest) } returns ApiResult.UnknownError("Unknown Error")

        viewModel.updateUiStateForTest(binInput = binRequest)
        viewModel.getBinInfo()

        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(LoadingState.Error(message = "Unknown Error"), viewModel.uiState.value.loadingState)
    }

    // END: test getBinInfo() area
}

private fun CheckBinScreenViewModel.updateUiStateForTest(
    binData: BinData? = null,
    binInput: String = "",
    isButtonEnabled: Boolean = false,
    loadingState: LoadingState? = null
) {
    val field = this.javaClass.getDeclaredField("_uiState")
    field.isAccessible = true
    @Suppress("UNCHECKED_CAST")
    val stateFlow = field.get(this) as MutableStateFlow<CheckBinScreenUiState>
    stateFlow.value = CheckBinScreenUiState(
        binData = binData,
        binInput = binInput,
        isButtonEnabled = isButtonEnabled,
        loadingState = loadingState
    )
}