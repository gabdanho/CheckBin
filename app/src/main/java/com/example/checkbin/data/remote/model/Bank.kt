package com.example.checkbin.data.remote.model

/**
 * Модель данных с информацией о банке-эмитенте карты.
 *
 * @property name Название банка
 * @property url Веб-сайт банка (URL)
 * @property phone Контактный телефон банка
 * @property city Город расположения банка-эмитента
 */
data class Bank(
    val name: String? = null,
    val url: String? = null,
    val phone: String? = null,
    val city: String? = null
)
