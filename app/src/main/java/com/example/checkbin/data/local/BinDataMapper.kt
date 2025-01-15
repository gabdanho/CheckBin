package com.example.checkbin.data.local

import com.example.whatbin.model.Bank
import com.example.whatbin.model.BinInfo
import com.example.whatbin.model.Country
import com.example.whatbin.model.NumberInfo

fun BinInfo.toBinData(): BinData {
    return BinData(
        number = this.number ?: NumberInfo(),
        scheme = this.scheme,
        type = this.type,
        brand = this.brand,
        prepaid = this.prepaid,
        country = this.country ?: Country(),
        bank = this.bank ?: Bank()
    )
}