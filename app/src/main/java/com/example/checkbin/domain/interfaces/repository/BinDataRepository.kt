package com.example.checkbin.domain.interfaces.repository

import com.example.checkbin.domain.model.data.BinData
import com.example.checkbin.domain.model.result.ApiResult

/**
 * Интерфейс для получения информации о BIN.
 * Используется для запроса данных о BIN с API.
 */
interface BinDataRepository {

    /**
     * Получает информацию о BIN по переданному значению.
     *
     * @param bin строка, представляющая BIN (первые 6–8 цифр номера карты).
     * @return [ApiResult] с объектом [BinData] в случае успеха, либо исключением при ошибке.
     */
    suspend fun getBinInfo(bin: String): ApiResult<BinData>
}
