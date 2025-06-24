package com.example.checkbin.data.remote.model

data class BinDataRequest(
    val number: NumberInfoRequest? = null,
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: Boolean? = null,
    val country: CountryRequest? = null,
    val bank: BankRequest? = null
)
