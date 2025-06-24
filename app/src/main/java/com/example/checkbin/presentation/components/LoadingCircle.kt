package com.example.checkbin.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.checkbin.presentation.theme.defaultDimensions

/**
 * Отображает индикатор загрузки.
 *
 * @param modifier модификатор компоновки (по умолчанию [Modifier]).
 */
@Composable
fun LoadingCircle(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(defaultDimensions.small)
    ) {
        CircularProgressIndicator()
    }
}
