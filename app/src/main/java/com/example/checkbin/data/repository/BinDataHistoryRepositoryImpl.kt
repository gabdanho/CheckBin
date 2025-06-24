package com.example.checkbin.data.repository

import com.example.checkbin.data.local.dao.BinDataHistoryDao
import com.example.checkbin.data.local.mapper.BinDataHistoryEntityMapper.toBinDataHistoryEntity
import com.example.checkbin.data.remote.model.BinDataRequest
import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import com.example.checkbin.presentation.mapper.BinDataMapper.toBinData
import com.example.checkbin.presentation.model.BinData
import javax.inject.Inject

class BinDataHistoryRepositoryImpl @Inject constructor(
    private val binDataHistoryDao: BinDataHistoryDao
): BinDataHistoryRepository {

    override suspend fun insertBinDataInHistory(binDataRequest: BinDataRequest) {
        binDataHistoryDao.insertBinDataInHistory(binDataRequest.toBinDataHistoryEntity())
    }

    override suspend fun getBinDataHistory(): List<BinData> {
        return binDataHistoryDao.getBinDataHistory().map { it.toBinData() }
    }
}