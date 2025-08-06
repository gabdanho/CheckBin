package com.example.checkbin.fake

import com.example.checkbin.data.local.model.entity.BankEntity
import com.example.checkbin.data.local.model.entity.BinDataHistoryEntity
import com.example.checkbin.data.local.model.entity.CountryEntity
import com.example.checkbin.data.local.model.entity.NumberInfoEntity
import com.example.checkbin.domain.model.data.Bank
import com.example.checkbin.domain.model.data.BinData
import com.example.checkbin.domain.model.data.Country
import com.example.checkbin.domain.model.data.NumberInfo

object BinDataFake {
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

    val binDataDomainFake = BinData(
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
            latitude = "50",
            longitude = "60"
        ),
        bank = Bank(
            name = "Chase Bank",
            url = "https://www.chase.com",
            phone = null,
            city = "New York"
        )
    )
}