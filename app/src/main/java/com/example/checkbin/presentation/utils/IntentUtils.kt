package com.example.checkbin.presentation.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.checkbin.R
import androidx.core.net.toUri

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
        if (number.isNotBlank()) {
            val intent = Intent(Intent.ACTION_DIAL, "tel:$number".toUri())
            safeStartActivity(intent)
        }
    }

    /**
     * Открывает карту с указанными координатами (широта и долгота).
     *
     * @param latitude широта точки на карте.
     * @param longitude долгота точки на карте.
     */
    fun Context.openMap(latitude: Int, longitude: Int) {
        if (latitude != 0 && longitude != 0) {
            val intent = Intent(Intent.ACTION_VIEW, "geo:$latitude,$longitude".toUri())
            safeStartActivity(intent)
        }
    }

    /**
     * Открывает браузер по заданной ссылке.
     *
     * @param link URL для открытия в браузере.
     */
    fun Context.openBrowser(link: String) {
        if (link.isNotBlank()) {
            val intent = Intent(Intent.ACTION_VIEW, link.toUri())
            safeStartActivity(intent)
        }
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
                getString(R.string.text_no_application_to_open_the_action),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
