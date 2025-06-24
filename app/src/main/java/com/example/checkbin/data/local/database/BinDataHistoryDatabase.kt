package com.example.checkbin.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.checkbin.data.local.dao.BinDataHistoryDao
import com.example.checkbin.data.local.model.entity.BinDataHistoryEntity

@Database(entities = [BinDataHistoryEntity::class], version = 6)
abstract class BinDataHistoryDatabase : RoomDatabase() {
    abstract fun binDataDao(): BinDataHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: BinDataHistoryDatabase? = null

        fun getDatabase(context: Context): BinDataHistoryDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    BinDataHistoryDatabase::class.java,
                    "bin_data_history"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}