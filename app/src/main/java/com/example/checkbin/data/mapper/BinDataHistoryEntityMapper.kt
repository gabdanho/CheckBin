package com.example.checkbin.data.mapper

import com.example.checkbin.data.local.model.entity.BankEntity
import com.example.checkbin.data.local.model.entity.BinDataHistoryEntity
import com.example.checkbin.data.local.model.entity.CountryEntity
import com.example.checkbin.data.local.model.entity.NumberInfoEntity
import com.example.checkbin.domain.model.data.BinData as BinDataDomain
import com.example.checkbin.domain.model.data.Bank as BankDomain
import com.example.checkbin.domain.model.data.Country as CountryDomain
import com.example.checkbin.domain.model.data.NumberInfo as NumberInfoDomain

/**
 * Предоставляет функции конвертации данных в формат для хранения в базе данных (BinDataHistoryEntity).
 */
object BinDataHistoryEntityMapper {

    /**
     * @receiver Исходный объект [BinDataDomain]
     * @return Новый экземпляр [BinDataHistoryEntity]
     */
    fun BinDataDomain.toBinDataHistoryEntity(): BinDataHistoryEntity {
        return BinDataHistoryEntity(
            number = NumberInfoEntity(
                length = this.number.length?.toInt(),
                luhn = this.number.luhn
            ),
            scheme = this.scheme,
            type = this.type,
            brand = this.brand,
            prepaid = this.prepaid,
            country = CountryEntity(
                numeric = this.country.numeric,
                alpha2 = this.country.alpha2,
                name = this.country.name,
                emoji = this.country.emoji,
                currency = this.country.currency,
                latitude = this.country.latitude?.toInt(),
                longitude = this.country.longitude?.toInt(),
            ),
            bank = BankEntity(
                name = this.bank.name,
                url = this.bank.url,
                phone = this.bank.phone,
                city = this.bank.city,
            )
        )
    }

    /**
     * @receiver Исходный объект [toBinDataHistoryEntity]
     * @return Новый экземпляр [BinDataDomain]
     */
    fun BinDataHistoryEntity.toDomain(): BinDataDomain {
        return BinDataDomain(
            number = NumberInfoDomain(
                length = this.number?.length.toString(),
                luhn = this.number?.luhn
            ),
            scheme = this.scheme,
            type = this.type,
            brand = this.brand,
            prepaid = this.prepaid,
            country = CountryDomain(
                numeric = this.country?.numeric,
                alpha2 = this.country?.alpha2,
                name = this.country?.name,
                emoji = this.country?.emoji,
                currency = this.country?.currency,
                latitude = this.country?.latitude.toString(),
                longitude = this.country?.longitude.toString(),
            ),
            bank = BankDomain(
                name = this.bank?.name,
                url = this.bank?.url,
                phone = this.bank?.phone,
                city = this.bank?.city,
            )
        )
    }
}