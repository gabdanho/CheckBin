package com.example.checkbin.ui

import com.example.checkbin.data.local.BinData
import com.example.whatbin.model.BinInfo

fun BinData.toBinInfo(): BinInfo {
    return BinInfo(
        number = this.number,
        scheme = this.scheme,
        type = this.type,
        brand = this.brand,
        prepaid = this.prepaid,
        country = this.country,
        bank = this.bank
    )
}