package com.example.checkbin.data.local.model

import android.content.ContentValues.TAG
import android.util.Log

/**
 * Выполняет безопасный вызов операции с базой данных Room.
 *
 * Блок [block] оборачивается в `try/catch`: при успешном выполнении возвращает результат,
 * при возникновении любого исключения логирует сообщение об ошибке с уровнем DEBUG и возвращает `null`.
 *
 * @param block Лямбда с suspend-функцией, выполняющей операцию чтения/записи в Room и возвращающей значение типа `T?`.
 * @return Результат выполнения [block] или `null`, если во время выполнения произошло исключение.
 */
suspend inline fun <T : Any> safeRoomCall(block: suspend () -> T?): T? {
    return try {
        block()
    } catch (e: Exception) {
        Log.d(TAG, e.message.toString())
        null
    }
}