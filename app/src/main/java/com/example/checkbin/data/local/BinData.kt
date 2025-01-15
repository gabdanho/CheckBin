package com.example.checkbin.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.whatbin.model.Bank
import com.example.whatbin.model.Country
import com.example.whatbin.model.NumberInfo

@Entity(tableName = "binData")
data class BinData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Embedded
    val number: NumberInfo? = NumberInfo(),
    val scheme: String? = "",
    val type: String? = "",
    val brand: String? = "",
    val prepaid: Boolean? = false,
    @Embedded(prefix = "country_")
    val country: Country? = Country(),
    @Embedded(prefix = "bank_")
    val bank: Bank? = Bank()
)
