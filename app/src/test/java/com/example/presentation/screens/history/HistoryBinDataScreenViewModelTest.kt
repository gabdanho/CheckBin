package com.example.presentation.screens.history

import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import com.example.checkbin.domain.model.data.Bank
import com.example.checkbin.domain.model.result.DbResult
import com.example.checkbin.presentation.mapper.BinDataMapper.toPresentation
import com.example.checkbin.presentation.model.LoadingState
import com.example.checkbin.presentation.screens.history.HistoryBinDataScreenViewModel
import com.example.fake.BinDataFake.binDataDomainFake
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HistoryBinDataScreenViewModelTest {

    private lateinit var viewModel: HistoryBinDataScreenViewModel
    private lateinit var binDataHistoryRepository: BinDataHistoryRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        binDataHistoryRepository = mockk(relaxed = true)
        viewModel = HistoryBinDataScreenViewModel(
            binDataHistoryRepository = binDataHistoryRepository,
            dispatcher = testDispatcher
        )
    }

    @After
    fun clear() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getHistoryInfo return empty list when database is empty`() = runTest {
        coEvery { binDataHistoryRepository.getBinDataHistory() } returns DbResult.Success(emptyList())

        viewModel.getHistoryInfoForTest()

        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { binDataHistoryRepository.getBinDataHistory() }
        assertTrue(viewModel.uiState.value.binHistory.isEmpty())
        assertEquals(LoadingState.Success, viewModel.uiState.value.loadingState)
    }

    @Test
    fun `getHistoryInfo return not empty list when database is not empty`() {
        val binDataHistory = listOf(
            binDataDomainFake,
            binDataDomainFake.copy(prepaid = null),
            binDataDomainFake.copy(bank = Bank(city = null))
        )

        coEvery { binDataHistoryRepository.getBinDataHistory() } returns DbResult.Success(binDataHistory)

        viewModel.getHistoryInfoForTest()

        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { binDataHistoryRepository.getBinDataHistory() }
        assertEquals(binDataHistory.map { it.toPresentation() }, viewModel.uiState.value.binHistory)
        assertEquals(LoadingState.Success, viewModel.uiState.value.loadingState)
    }

    @Test fun `getHistoryInfo return error when database return error`() {
        coEvery { binDataHistoryRepository.getBinDataHistory() } returns DbResult.Error("Unknown error")

        viewModel.getHistoryInfoForTest()

        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { binDataHistoryRepository.getBinDataHistory() }
        assertEquals(LoadingState.Error(message = "Unknown error"), viewModel.uiState.value.loadingState)
    }
}

private fun HistoryBinDataScreenViewModel.getHistoryInfoForTest() {
    val getHistory = this.javaClass.getDeclaredMethod("getHistoryInfo")
    getHistory.isAccessible = true
    getHistory.invoke(this)
}