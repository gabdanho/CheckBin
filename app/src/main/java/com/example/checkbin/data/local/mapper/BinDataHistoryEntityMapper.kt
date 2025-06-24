package com.example.checkbin.data.local.mapper

import com.example.checkbin.data.local.model.entity.BinDataHistoryEntity
import com.example.checkbin.data.remote.model.BankRequest
import com.example.checkbin.data.remote.model.BinDataRequest
import com.example.checkbin.data.remote.model.CountryRequest
import com.example.checkbin.data.remote.model.NumberInfoRequest

object BinDataHistoryEntityMapper {
    fun BinDataRequest.toBinDataHistoryEntity(): BinDataHistoryEntity {
        return BinDataHistoryEntity(
            number = NumberInfoRequest(
                length = this.number?.length,
                luhn = this.number?.luhn
            ),
            scheme = this.scheme,
            type = this.type,
            brand = this.brand,
            prepaid = this.prepaid,
            country = CountryRequest(
                numeric = this.country?.numeric,
                alpha2 = this.country?.alpha2,
                name = this.country?.name,
                emoji = this.country?.emoji,
                currency = this.country?.currency,
                latitude = this.country?.latitude,
                longitude = this.country?.longitude,
            ),
            bank = BankRequest(
                name = this.bank?.name,
                url = this.bank?.url,
                phone = this.bank?.phone,
                city = this.bank?.city,
            )
        )
    }
}