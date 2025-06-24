package com.example.checkbin.data.remote.model

/**
 * Основная модель данных ответа API для информации о BIN-коде карты.
 * Содержит полную информацию о банковской карте, ее эмитенте и стране происхождения.
 *
 * @property number Информация о номере карты
 * @property scheme Название платежной системы
 * @property type Тип карты
 * @property brand Бренд/категория карты
 * @property prepaid Флаг предоплаченной карты
 * @property country Данные о стране банка-эмитента
 * @property bank Информация о банке-эмитенте карты
 */
data class BinDataRequest(
    val number: NumberInfoRequest? = null,
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: Boolean? = null,
    val country: CountryRequest? = null,
    val bank: BankRequest? = null
)
