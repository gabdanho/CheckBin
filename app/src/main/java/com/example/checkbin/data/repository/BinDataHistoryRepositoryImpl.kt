package com.example.checkbin.data.repository

import com.example.checkbin.data.local.dao.BinDataHistoryDao
import com.example.checkbin.data.local.model.safeDbCall
import com.example.checkbin.data.mapper.BinDataHistoryEntityMapper.toBinDataHistoryEntity
import com.example.checkbin.data.mapper.BinDataHistoryEntityMapper.toDomain
import com.example.checkbin.domain.model.data.BinData as BinDataDomain
import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import com.example.checkbin.domain.model.data.BinData
import com.example.checkbin.domain.model.result.DbResult

/**
 * Реализация репозитория для работы с историей запросов BIN-данных.
 *
 * @param binDataHistoryDao DAO для доступа к данным истории
 */
class BinDataHistoryRepositoryImpl(
    private val binDataHistoryDao: BinDataHistoryDao
): BinDataHistoryRepository {

    /**
     * Сохраняет запрос BIN-данных в историю.
     * @param binData Данные для сохранения
     */
    override suspend fun insertBinDataInHistory(binData: BinDataDomain) {
        safeDbCall {
            binDataHistoryDao.insertBinDataInHistory(binData.toBinDataHistoryEntity())
        }
    }

    /**
     * Получает историю запросов BIN-данных.
     * @return Список [BinData]
     */
    override suspend fun getBinDataHistory(): DbResult<List<BinDataDomain>> {
        return safeDbCall {
            binDataHistoryDao.getBinDataHistory()?.map { it.toDomain() } ?: emptyList()
        }
    }
}