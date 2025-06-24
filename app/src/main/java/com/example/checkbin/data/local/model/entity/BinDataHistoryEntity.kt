package com.example.checkbin.data.local.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.checkbin.data.remote.model.BankRequest
import com.example.checkbin.data.remote.model.CountryRequest
import com.example.checkbin.data.remote.model.NumberInfoRequest

@Entity(tableName = BinDataHistoryEntity.TABLE_NAME)
data class BinDataHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Embedded
    val number: NumberInfoRequest? = NumberInfoRequest(),
    val scheme: String? = "",
    val type: String? = "",
    val brand: String? = "",
    val prepaid: Boolean? = false,
    @Embedded(prefix = PREFIX_COUNTRY)
    val country: CountryRequest? = CountryRequest(),
    @Embedded(prefix = PREFIX_BANK)
    val bank: BankRequest? = BankRequest()
) {
    companion object {
        const val TABLE_NAME = "binData"
        const val PREFIX_COUNTRY = "country_"
        const val PREFIX_BANK = "bank_"
    }
}
