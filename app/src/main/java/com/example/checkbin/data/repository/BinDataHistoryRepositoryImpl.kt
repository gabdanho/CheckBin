package com.example.checkbin.data.repository

import com.example.checkbin.data.local.dao.BinDataHistoryDao
import com.example.checkbin.data.local.mapper.BinDataHistoryEntityMapper.toBinDataHistoryEntity
import com.example.checkbin.data.local.model.safeRoomCall
import com.example.checkbin.data.remote.model.BinDataRequest
import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import com.example.checkbin.presentation.mapper.BinDataMapper.toBinData
import com.example.checkbin.presentation.model.BinData
import javax.inject.Inject

/**
 * Реализация репозитория для работы с историей запросов BIN-данных.
 *
 * @param binDataHistoryDao DAO для доступа к данным истории
 */
class BinDataHistoryRepositoryImpl @Inject constructor(
    private val binDataHistoryDao: BinDataHistoryDao
): BinDataHistoryRepository {

    /**
     * Сохраняет запрос BIN-данных в историю.
     * @param binDataRequest Данные для сохранения
     */
    override suspend fun insertBinDataInHistory(binDataRequest: BinDataRequest) {
        safeRoomCall {
            binDataHistoryDao.insertBinDataInHistory(binDataRequest.toBinDataHistoryEntity())
        }
    }

    /**
     * Получает историю запросов BIN-данных.
     * @return Список [BinDataRequest]
     */
    override suspend fun getBinDataHistory(): List<BinData>? {
        return safeRoomCall {
            binDataHistoryDao.getBinDataHistory()?.map { it.toBinData() }
        }
    }
}