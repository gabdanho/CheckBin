package com.example.checkbin.presentation.model

/**
 * Модель данных с информацией о банке-эмитенте карты.
 *
 * @property name Название банка
 * @property url Веб-сайт банка (URL)
 * @property phone Контактный телефон банка
 * @property city Город расположения банка-эмитента
 */
data class Bank(
    val name: String = "",
    val url: String = "",
    val phone: String = "",
    val city: String = ""
)

