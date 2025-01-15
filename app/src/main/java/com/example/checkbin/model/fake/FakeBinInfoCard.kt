package com.example.checkbin.model.fake

import com.example.whatbin.model.Bank
import com.example.whatbin.model.BinInfo
import com.example.whatbin.model.Country
import com.example.whatbin.model.NumberInfo

object FakeBinInfoCard {
    val binInfoCard = BinInfo(
        number = NumberInfo(16, true),
        scheme = "visa",
        type = "debit",
        brand = "Visa/Dankort",
        prepaid = true,
        country = Country("Denmark", "52"),
        bank = Bank("Sber", "https://sber.ru", "800", "Moscow")
    )
}