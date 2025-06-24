package com.example.checkbin.domain.interfaces.repository

import com.example.checkbin.data.remote.model.BinDataRequest
import com.example.checkbin.presentation.model.BinData

/**
 * Интерфейс для работы с историей данных BIN.
 * Предоставляет методы для добавления и получения истории запросов BIN.
 */
interface BinDataHistoryRepository {

    /**
     * Вставляет информацию о BIN-запросе в историю.
     *
     * @param binDataRequest данные запроса BIN, которые нужно сохранить в истории.
     */
    suspend fun insertBinDataInHistory(binDataRequest: BinDataRequest)

    /**
     * Возвращает список всех ранее сохранённых BIN-запросов.
     *
     * @return список объектов [BinData], представляющих историю BIN-запросов.
     */
    suspend fun getBinDataHistory(): List<BinData>?
}