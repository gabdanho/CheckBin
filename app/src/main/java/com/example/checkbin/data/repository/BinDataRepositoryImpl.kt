package com.example.checkbin.data.repository

import com.example.checkbin.data.remote.api.BinDataApi
import com.example.checkbin.data.remote.model.BinDataRequest
import com.example.checkbin.data.remote.safeApiCall
import com.example.checkbin.domain.interfaces.repository.BinDataRepository
import com.example.checkbin.domain.model.Result
import javax.inject.Inject

class BinDataRepositoryImpl @Inject constructor(
    private val binDataApi: BinDataApi
) : BinDataRepository {

    override suspend fun getBinInfo(bin: String): Result<BinDataRequest> {
        return safeApiCall {
            binDataApi.getBinData(bin)
        }
    }
}