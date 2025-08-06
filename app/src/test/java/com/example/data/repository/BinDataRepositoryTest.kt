package com.example.data.repository

import com.example.fake.BinDataFake.binDataDataFake
import com.example.checkbin.data.mapper.BinDataMapper.toDomain
import com.example.checkbin.data.remote.api.BinDataApi
import com.example.checkbin.data.repository.BinDataRepositoryImpl
import com.example.checkbin.domain.interfaces.repository.BinDataRepository
import com.example.checkbin.domain.model.result.ApiResult
import io.mockk.coEvery
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
class BinDataRepositoryTest {

    private lateinit var api: BinDataApi
    private lateinit var repository: BinDataRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        api = mockk()
        repository = BinDataRepositoryImpl(api)
    }

    @After
    fun clear() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getBinInfo should return ApiResult_Success when API call is succeed`() = runTest {
        val bin = "220070"
        val binData = binDataDataFake

        coEvery { api.getBinData(bin) } returns binData

        val result = repository.getBinInfo(bin)

        assertTrue(result is ApiResult.Success)
        assertEquals(binData.toDomain(), (result as ApiResult.Success).data)
    }

    @Test
    fun `getBinInfo should return ApiResult_Error when API call fails`() = runTest {
        val bin = "220070"

        coEvery { api.getBinData(bin) } throws Exception("API Error")

        val result = repository.getBinInfo(bin)

        assertTrue(result is ApiResult.UnknownError)
        assertEquals("java.lang.Exception: API Error", (result as ApiResult.UnknownError).message)
    }
}