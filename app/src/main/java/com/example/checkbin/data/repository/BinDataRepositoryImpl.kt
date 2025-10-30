package com.example.checkbin.data.repository

import com.example.checkbin.data.mapper.BinDataMapper.toDomain
import com.example.checkbin.data.remote.api.BinDataApi
import com.example.checkbin.data.remote.model.safeApiCall
import com.example.checkbin.domain.model.data.BinData as BinDataDomain
import com.example.checkbin.domain.interfaces.repository.BinDataRepository
import com.example.checkbin.domain.model.result.ApiResult

/**
 * Реализация репозитория для работы с API BIN-данных.
 *
 * @param binDataApi API-клиент для запросов BIN-данных
 */
class BinDataRepositoryImpl(
    private val binDataApi: BinDataApi
) : BinDataRepository {

    /**
     * Получает информацию о BIN-коде из API.
     * @param bin BIN-код для проверки
     * @return Результат запроса [ApiResult] с данными или ошибкой
     */
    override suspend fun getBinInfo(bin: String): ApiResult<BinDataDomain> {
        return safeApiCall {
            binDataApi.getBinData(bin).toDomain()
        }
    }
}