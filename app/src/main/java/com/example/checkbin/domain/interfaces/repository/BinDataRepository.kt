package com.example.checkbin.domain.interfaces.repository

import com.example.checkbin.data.remote.model.BinDataRequest
import com.example.checkbin.domain.model.Result

interface BinDataRepository {

    suspend fun getBinInfo(bin: String): Result<BinDataRequest>
}