package com.example.checkbin.data.local.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.checkbin.data.remote.model.BankRequest
import com.example.checkbin.data.remote.model.CountryRequest
import com.example.checkbin.data.remote.model.NumberInfoRequest

/**
 * Сущность для хранения истории запросов BIN-кодов в базе данных Room.
 *
 * @property id Уникальный идентификатор записи (автогенерируемый)
 * @property number Информация о номере карты
 * @property scheme Платежная система
 * @property type Тип карты
 * @property brand Бренд карты
 * @property prepaid Признак предоплаченной карты
 * @property country Информация о стране банка-эмитента
 * @property bank Информация о банке-эмитенте
 */
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
        /**
         * Название таблицы в базе данных.
         */
        const val TABLE_NAME = "binData"

        /**
         * Префикс для полей страны банка в таблице.
         */
        const val PREFIX_COUNTRY = "country_"

        /**
         * Префикс для полей банка в таблице.
         */
        const val PREFIX_BANK = "bank_"
    }
}
