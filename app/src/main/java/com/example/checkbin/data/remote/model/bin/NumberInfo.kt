package com.example.checkbin.data.remote.model.bin

/**
 * Модель данных с информацией о номере банковской карты.
 * Содержит технические характеристики номера карты.
 *
 * @property length Длина номера карты (количество цифр)
 * @property luhn Результат проверки номера по алгоритму Луна
 */
data class NumberInfo(
    val length: Int? = null,
    val luhn: Boolean? = null
)