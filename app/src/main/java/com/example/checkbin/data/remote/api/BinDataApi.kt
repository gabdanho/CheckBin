package com.example.checkbin.data.remote.api

import com.example.checkbin.data.remote.model.bin.BinData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/**
 * Класс API для получения информации о BIN-кодах банковских карт.
 *
 * Определяет конечные точки (endpoints) для работы с внешним сервисом проверки BIN-кодов.
 */
class BinDataApi(private val client: HttpClient) {

    /**
     * Получает информацию о BIN-коде банковской карты.
     *
     * @param bin BIN-код (первые 6-8 цифр номера карты) для проверки
     * @return Ответ сервера в виде [BinData] с деталями о карте
     */
    suspend fun getBinData(bin: String): BinData {
        return client.get("$BASE_URL/$bin").body()
    }

    companion object {
        const val BASE_URL = "https://lookup.binlist.net"
    }
}