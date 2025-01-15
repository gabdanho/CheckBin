package com.example.checkbin.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BinData::class], version = 6)
abstract class BinDataDatabase : RoomDatabase() {
    abstract fun binDataDao(): BinDataDao

    companion object {
        @Volatile
        private var INSTANCE: BinDataDatabase? = null

        fun getDatabase(context: Context): BinDataDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    BinDataDatabase::class.java,
                    "bin_data"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}