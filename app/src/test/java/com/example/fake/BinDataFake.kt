package com.example.fake

import com.example.checkbin.data.local.model.entity.BankEntity
import com.example.checkbin.data.local.model.entity.BinDataHistoryEntity
import com.example.checkbin.data.local.model.entity.CountryEntity
import com.example.checkbin.data.local.model.entity.NumberInfoEntity
import com.example.checkbin.data.remote.model.bin.Bank
import com.example.checkbin.data.remote.model.bin.BinData
import com.example.checkbin.data.remote.model.bin.Country
import com.example.checkbin.data.remote.model.bin.NumberInfo
import com.example.checkbin.domain.model.data.Bank as BankDomain
import com.example.checkbin.domain.model.data.BinData as BinDataDomain
import com.example.checkbin.domain.model.data.Country as CountryDomain
import com.example.checkbin.domain.model.data.NumberInfo as NumberInfoDomain

object BinDataFake {
    val binDataDomainFake = BinDataDomain(
        number = NumberInfoDomain(
            length = null,
            luhn = true
        ),
        scheme = "visa",
        type = "debit",
        brand = "Visa Classic",
        prepaid = false,
        country = CountryDomain(
            numeric = "840",
            alpha2 = "US",
            name = "United States",
            emoji = "🇺🇸",
            currency = "USD",
            latitude = "50",
            longitude = "60"
        ),
        bank = BankDomain(
            name = "Chase Bank",
            url = "https://www.chase.com",
            phone = null,
            city = "New York"
        )
    )

    val binDataDataFake = BinData(
        number = NumberInfo(
            length = null,
            luhn = true
        ),
        scheme = "visa",
        type = "debit",
        brand = "Visa Classic",
        prepaid = false,
        country = Country(
            numeric = "840",
            alpha2 = "US",
            name = "United States",
            emoji = "🇺🇸",
            currency = "USD",
            latitude = 50,
            longitude = 60
        ),
        bank = Bank(
            name = "Chase Bank",
            url = "https://www.chase.com",
            phone = null,
            city = "New York"
        )
    )

    val binDataHistoryEntityFake = BinDataHistoryEntity(
        number = NumberInfoEntity(
            length = 16,
            luhn = true
        ),
        scheme = "visa",
        type = "debit",
        brand = "Visa Classic",
        prepaid = false,
        country = CountryEntity(
            numeric = "840",
            alpha2 = "US",
            name = "United States",
            emoji = "🇺🇸",
            currency = "USD"
        ),
        bank = BankEntity(
            name = "Chase Bank",
            url = "https://www.chase.com",
            phone = "+18009359935",
            city = "New York"
        )
    )
}