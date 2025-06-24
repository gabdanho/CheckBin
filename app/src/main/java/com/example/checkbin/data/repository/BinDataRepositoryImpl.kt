package com.example.checkbin.data.repository

import com.example.checkbin.data.remote.api.BinDataApi
import com.example.checkbin.data.remote.model.BinDataRequest
import com.example.checkbin.data.remote.safeApiCall
import com.example.checkbin.domain.interfaces.repository.BinDataRepository
import com.example.checkbin.domain.model.Result
import javax.inject.Inject

/**
 * Реализация репозитория для работы с API BIN-данных.
 *
 * @param binDataApi API-клиент для запросов BIN-данных
 */
class BinDataRepositoryImpl @Inject constructor(
    private val binDataApi: BinDataApi
) : BinDataRepository {

    /**
     * Получает информацию о BIN-коде из API.
     * @param bin BIN-код для проверки
     * @return Результат запроса [Result] с данными или ошибкой
     */
    override suspend fun getBinInfo(bin: String): Result<BinDataRequest> {
        return safeApiCall {
            binDataApi.getBinData(bin)
        }
    }
}