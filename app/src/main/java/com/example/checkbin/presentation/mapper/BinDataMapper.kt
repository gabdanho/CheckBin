package com.example.checkbin.presentation.mapper

import com.example.checkbin.data.local.model.entity.BinDataHistoryEntity
import com.example.checkbin.data.remote.model.BinDataRequest
import com.example.checkbin.presentation.model.Bank
import com.example.checkbin.presentation.model.BinData
import com.example.checkbin.presentation.model.Country
import com.example.checkbin.presentation.model.NumberInfo

object BinDataMapper {
    fun BinDataRequest.toBinData(): BinData {
        return BinData(
            number = NumberInfo(
                length = this.number?.length.toString(),
                luhn = convertBooleanToString(this.number?.luhn)
            ),
            scheme = this.scheme ?: "Unknown",
            type = this.type ?: "Unknown",
            brand = this.brand ?: "Unknown",
            prepaid = convertBooleanToString(this.prepaid),
            country = Country(
                numeric = this.country?.numeric ?: "Unknown",
                alpha2 = this.country?.alpha2 ?: "Unknown",
                name = this.country?.name ?: "Unknown",
                emoji = this.country?.emoji ?: "Unknown",
                currency = this.country?.currency ?: "Unknown",
                latitude = this.country?.latitude.toString(),
                longitude = this.country?.longitude.toString(),
            ),
            bank = Bank(
                name = this.bank?.name ?: "Unknown",
                url = this.bank?.url ?: "Unknown",
                phone = this.bank?.phone ?: "Unknown",
                city = this.bank?.city ?: "Unknown",
            )
        )
    }

    fun BinDataHistoryEntity.toBinData(): BinData {
        return BinData(
            number = NumberInfo(
                length = this.number?.length.toString(),
                luhn = convertBooleanToString(this.number?.luhn)
            ),
            scheme = this.scheme ?: "Unknown",
            type = this.type ?: "Unknown",
            brand = this.brand ?: "Unknown",
            prepaid = convertBooleanToString(this.prepaid),
            country = Country(
                numeric = this.country?.numeric ?: "Unknown",
                alpha2 = this.country?.alpha2 ?: "Unknown",
                name = this.country?.name ?: "Unknown",
                emoji = this.country?.emoji ?: "Unknown",
                currency = this.country?.currency ?: "Unknown",
                latitude = this.country?.latitude.toString(),
                longitude = this.country?.longitude.toString(),
            ),
            bank = Bank(
                name = this.bank?.name ?: "Unknown",
                url = this.bank?.url ?: "Unknown",
                phone = this.bank?.phone ?: "Unknown",
                city = this.bank?.city ?: "Unknown",
            )
        )
    }
}

private fun convertBooleanToString(value: Boolean?): String {
    return when(value) {
        true -> "Yes"
        false -> "No"
        else -> "Unknown"
    }
}