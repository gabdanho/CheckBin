package com.example.checkbin.presentation.utils

import androidx.annotation.StringRes
import com.example.checkbin.R

/**
 * Утилиты упрощения работы над ресурсами строк.
 */
object StringResUtils {

    /**
     * Функция преобразование булевого значения в ресурс строки.
     *
     * @param value Булевое значение
     */
    @StringRes
    fun booleanToStringRes(value: Boolean?): Int {
        return when(value) {
            true -> R.string.text_yes
            false -> R.string.text_no
            else -> R.string.text_nothing
        }
    }
}