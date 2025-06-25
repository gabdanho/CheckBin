package com.example.checkbin.presentation.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

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
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        safeStartActivity(intent)
    }

    /**
     * Открывает карту с указанными координатами (широта и долгота).
     *
     * @param latitude широта точки на карте.
     * @param longitude долгота точки на карте.
     */
    fun Context.openMap(latitude: Int, longitude: Int) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$latitude,$longitude"))
        safeStartActivity(intent)
    }

    /**
     * Открывает браузер по заданной ссылке.
     *
     * @param link URL для открытия в браузере.
     */
    fun Context.openBrowser(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        safeStartActivity(intent)
    }

    /**
     * Безопасно выполняет запуск внешней активности по переданному интенту.
     *
     * @param intent Намерение, которое нужно запустить.
     */
    private fun Context.safeStartActivity(intent: Intent) {
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(
                this,
                "There is no suitable application to perform the action.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
