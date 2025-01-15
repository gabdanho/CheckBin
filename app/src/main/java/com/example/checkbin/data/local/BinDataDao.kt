package com.example.checkbin.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BinDataDao {
    @Insert
    suspend fun insertBinData(binData: BinData)

    @Query("SELECT * FROM binData ORDER BY id DESC")
    fun getBinData(): Flow<List<BinData>>
}