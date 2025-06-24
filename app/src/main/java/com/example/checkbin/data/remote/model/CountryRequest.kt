package com.example.checkbin.data.remote.model

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
data class CountryRequest(
    val numeric: String? = null,
    val alpha2: String? = null,
    val name: String? = null,
    val emoji: String? = null,
    val currency: String? = null,
    val latitude: Int? = null,
    val longitude: Int? = null
)
