package com.example.checkbin.data.local.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.checkbin.data.local.dao.BinDataHistoryDao
import com.example.checkbin.data.local.model.entity.BinDataHistoryEntity
import java.util.concurrent.Executors

/**
 * Базы данных Room для хранения истории запросов BIN-кодов.
 *
 * Определяет конфигурацию базы данных и предоставляет доступ к DAO (Data Access Object).
 */
@Database(entities = [BinDataHistoryEntity::class], version = 6)
abstract class BinDataHistoryDatabase : RoomDatabase() {
    /**
     * Предоставляет доступ к методам работы с историей BIN-кодов.
     *
     * @return Экземпляр [BinDataHistoryDao] для выполнения операций с базой данных
     */
    abstract fun binDataDao(): BinDataHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: BinDataHistoryDatabase? = null

        /**
         * Получает экземпляр базы данных
         *
         * @param context Контекст приложения
         * @return Единый экземпляр [BinDataHistoryDatabase]
         */
        fun getDatabase(context: Context): BinDataHistoryDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    BinDataHistoryDatabase::class.java,
                    "bin_data_history"
                )
                    .fallbackToDestructiveMigration(false)
                    .setJournalMode(JournalMode.TRUNCATE)
                    .setQueryCallback({ sqlQuery, _ -> Log.d("RoomQuery", sqlQuery) }, Executors.newSingleThreadExecutor())
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}