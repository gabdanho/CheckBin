package com.example.checkbin.presentation.model

data class BinData(
    val number: NumberInfo = NumberInfo(),
    val scheme: String = "",
    val type: String = "",
    val brand: String = "",
    val prepaid: String = "",
    val country: Country = Country(),
    val bank: Bank = Bank()
)