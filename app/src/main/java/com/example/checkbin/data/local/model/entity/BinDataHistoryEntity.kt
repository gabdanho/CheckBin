package com.example.checkbin.data.local.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    val number: NumberInfoEntity? = NumberInfoEntity(),
    val scheme: String? = "",
    val type: String? = "",
    val brand: String? = "",
    val prepaid: Boolean? = false,
    @Embedded(prefix = PREFIX_COUNTRY)
    val country: CountryEntity? = CountryEntity(),
    @Embedded(prefix = PREFIX_BANK)
    val bank: BankEntity? = BankEntity()
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

/**
 * Модель данных с информацией о банке-эмитенте карты.
 *
 * @property name Название банка
 * @property url Веб-сайт банка (URL)
 * @property phone Контактный телефон банка
 * @property city Город расположения банка-эмитента
 */
data class BankEntity(
    val name: String? = null,
    val url: String? = null,
    val phone: String? = null,
    val city: String? = null
)

/**
 * Модель данных страны банка-эмитента карты.
 * Содержит географическую и административную информацию о стране.
 *
 * @property numeric Цифровой код страны
 * @property alpha2 Двухбуквенный код страны
 * @property name Официальное название страны
 * @property emoji Эмоджи флага страны
 * @property currency Код валюты страны
 * @property latitude Географическая широта столицы
 * @property longitude Географическая долгота столицы
 */
data class CountryEntity(
    val numeric: String? = null,
    val alpha2: String? = null,
    val name: String? = null,
    val emoji: String? = null,
    val currency: String? = null,
    val latitude: Int? = null,
    val longitude: Int? = null
)

/**
 * Модель данных с информацией о номере банковской карты.
 * Содержит технические характеристики номера карты.
 *
 * @property length Длина номера карты (количество цифр)
 * @property luhn Результат проверки номера по алгоритму Луна
 */
data class NumberInfoEntity(
    val length: Int? = null,
    val luhn: Boolean? = null
)