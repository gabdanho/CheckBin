package com.example.checkbin.presentation.model.bin

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
data class Country(
    val numeric: String? = "",
    val alpha2: String? = "",
    val name: String? = "",
    val emoji: String? = "",
    val currency: String? = "",
    val latitude: String? = "",
    val longitude: String? = ""
)
