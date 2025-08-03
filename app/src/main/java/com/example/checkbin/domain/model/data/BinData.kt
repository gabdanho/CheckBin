package com.example.checkbin.domain.model.data

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
data class BinData(
    val number: NumberInfo = NumberInfo(),
    val scheme: String? = "",
    val type: String? = "",
    val brand: String? = "",
    val prepaid: Boolean? = null,
    val country: Country = Country(),
    val bank: Bank = Bank()
)