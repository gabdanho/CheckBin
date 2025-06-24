package com.example.checkbin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.checkbin.data.local.model.entity.BinDataHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BinDataHistoryDao {
    @Insert
    suspend fun insertBinDataInHistory(binData: BinDataHistoryEntity)

    @Query("SELECT * FROM binData ORDER BY id DESC")
    suspend fun getBinDataHistory(): List<BinDataHistoryEntity>
}