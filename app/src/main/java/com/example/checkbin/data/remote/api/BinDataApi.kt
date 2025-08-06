package com.example.checkbin.data.remote.api

import com.example.checkbin.data.remote.model.bin.BinData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Интерфейс API для получения информации о BIN-кодах банковских карт.
 *
 * Определяет конечные точки (endpoints) для работы с внешним сервисом проверки BIN-кодов.
 */
interface BinDataApi {

    /**
     * Получает информацию о BIN-коде банковской карты.
     *
     * @param bin BIN-код (первые 6-8 цифр номера карты) для проверки
     * @return Ответ сервера в виде [BinData] с деталями о карте
     */
    @Headers("Accept-Version: 3")
    @GET("{bin}")
    suspend fun getBinData(@Path("bin") bin: String): BinData
}