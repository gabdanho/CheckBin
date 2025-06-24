package com.example.checkbin.domain.interfaces.repository

import com.example.checkbin.data.remote.model.BinDataRequest
import com.example.checkbin.domain.model.Result

/**
 * Интерфейс для получения информации о BIN.
 * Используется для запроса данных о BIN с API.
 */
interface BinDataRepository {

    /**
     * Получает информацию о BIN по переданному значению.
     *
     * @param bin строка, представляющая BIN (первые 6–8 цифр номера карты).
     * @return [Result] с объектом [BinDataRequest] в случае успеха, либо исключением при ошибке.
     */
    suspend fun getBinInfo(bin: String): Result<BinDataRequest>
}
