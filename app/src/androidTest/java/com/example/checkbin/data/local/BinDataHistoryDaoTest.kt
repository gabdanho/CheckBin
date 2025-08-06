package com.example.checkbin.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.checkbin.fake.BinDataFake.binDataHistoryEntityFake
import com.example.checkbin.data.local.dao.BinDataHistoryDao
import com.example.checkbin.data.local.database.BinDataHistoryDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BinDataHistoryDaoTest {

    private lateinit var database: BinDataHistoryDatabase
    private lateinit var dao: BinDataHistoryDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BinDataHistoryDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = database.binDataDao()
    }

    @After
    fun clear() {
        database.close()
    }

    @Test
    fun insert_and_getBinDataHistory_returns_same_data() = runBlocking {
        var binData = binDataHistoryEntityFake

        dao.insertBinDataInHistory(binData = binData)

        val history = dao.getBinDataHistory()
        requireNotNull(history) { "History is empty" }
        val dbItem = history.first()
        binData = binData.copy(id = dbItem.id)

        assertTrue(history.isNotEmpty())
        assertEquals(dbItem, binData)
    }
}