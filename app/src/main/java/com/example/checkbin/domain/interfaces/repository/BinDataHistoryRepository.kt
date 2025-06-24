package com.example.checkbin.domain.interfaces.repository

import com.example.checkbin.data.remote.model.BinDataRequest
import com.example.checkbin.presentation.model.BinData

interface BinDataHistoryRepository {

    suspend fun insertBinDataInHistory(binDataRequest: BinDataRequest)

    suspend fun getBinDataHistory(): List<BinData>
}