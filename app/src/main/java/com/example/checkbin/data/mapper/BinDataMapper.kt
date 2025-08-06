package com.example.checkbin.data.mapper

import com.example.checkbin.data.remote.model.bin.BinData
import com.example.checkbin.domain.model.data.NumberInfo as NumberInfoDomain
import com.example.checkbin.domain.model.data.Country as CountryDomain
import com.example.checkbin.domain.model.data.Bank as BankDomain
import com.example.checkbin.domain.model.data.BinData as BinDataDomain

object BinDataMapper {

    /**
     * Преобразует [BinData] в объект [BinData].
     *
     * @receiver объект [BinData] для преобразования.
     * @return объект [BinData].
     */
    fun BinData.toDomain(): BinDataDomain {
        return BinDataDomain(
            number = NumberInfoDomain(
                length = if (this.number?.length != null) this.number.length.toString() else null,
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
                latitude = if (this.country?.latitude != null) this.country.latitude.toString() else null,
                longitude = if (this.country?.longitude != null) this.country.longitude.toString() else null,
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