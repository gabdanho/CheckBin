package com.example.checkbin.presentation.mapper

import com.example.checkbin.data.local.model.entity.BinDataHistoryEntity
import com.example.checkbin.data.remote.model.BinDataRequest
import com.example.checkbin.presentation.model.Bank
import com.example.checkbin.presentation.model.BinData
import com.example.checkbin.presentation.model.Country
import com.example.checkbin.presentation.model.NumberInfo

/**
 * Маппер, содержащий утилиты для преобразования различных источников данных в модель [BinData].
 */
object BinDataMapper {

    /** Стандартное значение по умолчанию для неизвестных или отсутствующих данных. */
    const val UNKNOWN_VALUE = "Unknown"

    /** Внутреннее значение, представляющее булевое значение `true` в виде строки. */
    private const val YES_VALUE = "Yes"

    /** Внутреннее значение, представляющее булевое значение `false` в виде строки. */
    private const val NO_VALUE = "No"

    /**
     * Преобразует [BinDataRequest] в объект [BinData].
     *
     * Все отсутствующие значения заменяются на [UNKNOWN_VALUE].
     *
     * @receiver объект [BinDataRequest] для преобразования.
     * @return объект [BinData].
     */
    fun BinDataRequest.toBinData(): BinData {
        return BinData(
            number = NumberInfo(
                length = this.number?.length.toString(),
                luhn = convertBooleanToString(this.number?.luhn)
            ),
            scheme = this.scheme ?: UNKNOWN_VALUE,
            type = this.type ?: UNKNOWN_VALUE,
            brand = this.brand ?: UNKNOWN_VALUE,
            prepaid = convertBooleanToString(this.prepaid),
            country = Country(
                numeric = this.country?.numeric ?: UNKNOWN_VALUE,
                alpha2 = this.country?.alpha2 ?: UNKNOWN_VALUE,
                name = this.country?.name ?: UNKNOWN_VALUE,
                emoji = this.country?.emoji ?: UNKNOWN_VALUE,
                currency = this.country?.currency ?: UNKNOWN_VALUE,
                latitude = this.country?.latitude.toString(),
                longitude = this.country?.longitude.toString(),
            ),
            bank = Bank(
                name = this.bank?.name ?: UNKNOWN_VALUE,
                url = this.bank?.url ?: UNKNOWN_VALUE,
                phone = this.bank?.phone ?: UNKNOWN_VALUE,
                city = this.bank?.city ?: UNKNOWN_VALUE,
            )
        )
    }

    /**
     * Преобразует [BinDataHistoryEntity] в объект [BinData].
     *
     * Все отсутствующие значения заменяются на [UNKNOWN_VALUE].
     *
     * @receiver объект [BinDataHistoryEntity] для преобразования.
     * @return объект [BinData].
     */
    fun BinDataHistoryEntity.toBinData(): BinData {
        return BinData(
            number = NumberInfo(
                length = this.number?.length.toString(),
                luhn = convertBooleanToString(this.number?.luhn)
            ),
            scheme = this.scheme ?: UNKNOWN_VALUE,
            type = this.type ?: UNKNOWN_VALUE,
            brand = this.brand ?: UNKNOWN_VALUE,
            prepaid = convertBooleanToString(this.prepaid),
            country = Country(
                numeric = this.country?.numeric ?: UNKNOWN_VALUE,
                alpha2 = this.country?.alpha2 ?: UNKNOWN_VALUE,
                name = this.country?.name ?: UNKNOWN_VALUE,
                emoji = this.country?.emoji ?: UNKNOWN_VALUE,
                currency = this.country?.currency ?: UNKNOWN_VALUE,
                latitude = this.country?.latitude.toString(),
                longitude = this.country?.longitude.toString(),
            ),
            bank = Bank(
                name = this.bank?.name ?: UNKNOWN_VALUE,
                url = this.bank?.url ?: UNKNOWN_VALUE,
                phone = this.bank?.phone ?: UNKNOWN_VALUE,
                city = this.bank?.city ?: UNKNOWN_VALUE,
            )
        )
    }

    /**
     * Преобразует булево значение в строковое представление.
     *
     * @param value значение типа `Boolean?`, которое может быть null.
     * @return "Yes" для `true`, "No" для `false`, "Unknown" для `null`.
     */
    private fun convertBooleanToString(value: Boolean?): String {
        return when(value) {
            true -> YES_VALUE
            false -> NO_VALUE
            else -> UNKNOWN_VALUE
        }
    }
}