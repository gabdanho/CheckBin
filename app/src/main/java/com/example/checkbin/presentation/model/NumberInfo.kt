package com.example.checkbin.presentation.model

/**
 * Модель данных с информацией о номере банковской карты.
 * Содержит технические характеристики номера карты.
 *
 * @property length Длина номера карты (количество цифр)
 * @property luhn Результат проверки номера по алгоритму Луна
 */
data class NumberInfo(
    val length: String = "",
    val luhn: String = ""
)