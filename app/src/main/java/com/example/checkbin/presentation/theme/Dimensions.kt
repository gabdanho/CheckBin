package com.example.checkbin.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Набор размерных значений для отступов и веса элементов в интерфейсе.
 */
data class Dimensions(
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val fullWeight: Float
)

/** Значения размеров по умолчанию для использования в UI. */
val defaultDimensions = Dimensions(
    small = 8.dp,
    medium = 16.dp,
    large = 32.dp,
    fullWeight = 1f
)
