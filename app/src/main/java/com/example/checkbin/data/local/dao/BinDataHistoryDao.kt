package com.example.checkbin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.checkbin.data.local.model.entity.BinDataHistoryEntity

/**
 * Data Access Object (DAO) для работы с историей запросов BIN-данных в локальной базе данных.
 *
 * Предоставляет методы для добавления новых записей и получения истории ранее проверенных BIN-кодов.
 */
@Dao
interface BinDataHistoryDao {

    /**
     * Добавляет новую запись о проверке BIN-кода в историю.
     *
     * @param binData Сущность [BinDataHistoryEntity] с данными о проверке BIN-кода
     */
    @Insert
    suspend fun insertBinDataInHistory(binData: BinDataHistoryEntity)

    /**
     * Получает всю историю проверенных BIN-кодов из базы данных.
     * Записи возвращаются в обратном хронологическом порядке (новые сначала).
     *
     * @return Список сущностей [BinDataHistoryEntity] с историей запросов
     */
    @Query("SELECT * FROM binData ORDER BY id DESC")
    suspend fun getBinDataHistory(): List<BinDataHistoryEntity>?
}