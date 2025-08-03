package com.example.checkbin.domain.interfaces.repository

import com.example.checkbin.domain.model.data.BinData
import com.example.checkbin.domain.model.result.DbResult

/**
 * Интерфейс для работы с историей данных BIN.
 * Предоставляет методы для добавления и получения истории запросов BIN.
 */
interface BinDataHistoryRepository {

    /**
     * Вставляет информацию о BIN-запросе в историю.
     *
     * @param binData данные запроса BIN, которые нужно сохранить в истории.
     */
    suspend fun insertBinDataInHistory(binData: BinData)

    /**
     * Возвращает список всех ранее сохранённых BIN-запросов.
     *
     * @return список объектов [BinData], представляющих историю BIN-запросов.
     */
    suspend fun getBinDataHistory(): DbResult<List<BinData>>
}