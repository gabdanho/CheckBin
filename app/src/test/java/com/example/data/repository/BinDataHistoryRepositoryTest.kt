package com.example.data.repository

import com.example.fake.BinDataFake.binDataDomainFake
import com.example.fake.BinDataFake.binDataHistoryEntityFake
import com.example.checkbin.data.local.dao.BinDataHistoryDao
import com.example.checkbin.data.mapper.BinDataHistoryEntityMapper.toBinDataHistoryEntity
import com.example.checkbin.data.mapper.BinDataHistoryEntityMapper.toDomain
import com.example.checkbin.data.repository.BinDataHistoryRepositoryImpl
import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import com.example.checkbin.domain.model.result.DbResult
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
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

@ExperimentalCoroutinesApi
class BinDataHistoryRepositoryTest {

    private lateinit var dao: BinDataHistoryDao
    private lateinit var repository: BinDataHistoryRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        dao = mockk()
        repository = BinDataHistoryRepositoryImpl(dao)
    }

    @After
    fun clear() {
        Dispatchers.resetMain()
    }

    @Test
    fun `insertBinDataInHistory stores in db the mapped BinData`() = runTest {
        val binData = binDataDomainFake
        val binDataHistoryEntity = binData.toBinDataHistoryEntity()

        coEvery { dao.insertBinDataInHistory(binDataHistoryEntity) } just Runs
        repository.insertBinDataInHistory(binData)
        coVerify { dao.insertBinDataInHistory(binDataHistoryEntity) }
    }

    @Test
    fun `getBinDataHistory should return a list of BinData`() = runTest {
        val entityList = listOf(binDataHistoryEntityFake)
        val binDataDomainList = entityList.map { it.toDomain() }

        coEvery { dao.getBinDataHistory() } returns entityList

        val result = repository.getBinDataHistory()

        assertTrue(result is DbResult.Success)
        assertEquals(binDataDomainList, (result as DbResult.Success).data)
    }

    @Test
    fun `getBinDataHistory should return an error when db call fails`() = runTest {
        coEvery { dao.getBinDataHistory() } throws Exception("DB Error")

        val result = repository.getBinDataHistory()

        assertTrue(result is DbResult.Error)
        assertEquals("DB Error", (result as DbResult.Error).message)
    }
}