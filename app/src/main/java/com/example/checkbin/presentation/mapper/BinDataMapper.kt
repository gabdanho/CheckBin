package com.example.checkbin.presentation.mapper

import com.example.checkbin.presentation.model.Bank
import com.example.checkbin.presentation.model.BinData
import com.example.checkbin.presentation.model.Country
import com.example.checkbin.presentation.model.NumberInfo
import com.example.checkbin.domain.model.data.BinData as BinDataDomain

/**
 * Маппер, содержащий утилиты для преобразования различных источников данных в модель [BinData].
 */
object BinDataMapper {

    /**
     * Преобразует [BinDataDomain] в объект [BinData].
     *
     * @receiver объект [BinDataDomain] для преобразования.
     * @return объект [BinData].
     */
    fun BinDataDomain.toPresentation(): BinData {
        return BinData(
            number = NumberInfo(
                length = this.number.length.toString(),
                luhn = this.number.luhn
            ),
            scheme = this.scheme,
            type = this.type,
            brand = this.brand,
            prepaid = this.prepaid,
            country = Country(
                numeric = this.country.numeric,
                alpha2 = this.country.alpha2,
                name = this.country.name,
                emoji = this.country.emoji,
                currency = this.country.currency,
                latitude = this.country.latitude.toString(),
                longitude = this.country.longitude.toString(),
            ),
            bank = Bank(
                name = this.bank.name,
                url = this.bank.url,
                phone = this.bank.phone,
                city = this.bank.city,
            )
        )
    }
}