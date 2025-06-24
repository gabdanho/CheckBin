package com.example.checkbin.presentation.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Утилиты для упрощённого создания и запуска интентов из контекста.
 */
object IntentUtils {

    /**
     * Открывает приложение телефона для звонка по заданному номеру.
     *
     * @param number телефонный номер, на который будет совершен звонок.
     */
    fun Context.openPhone(number: String) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
        this.startActivity(intent)
    }

    /**
     * Открывает карту с указанными координатами (широта и долгота).
     *
     * @param latitude широта точки на карте.
     * @param longitude долгота точки на карте.
     */
    fun Context.openMap(latitude: Int, longitude: Int) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$latitude,$longitude"))
        this.startActivity(intent)
    }

    /**
     * Открывает браузер по заданной ссылке.
     *
     * @param link URL для открытия в браузере.
     */
    fun Context.openBrowser(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        this.startActivity(intent)
    }
}
