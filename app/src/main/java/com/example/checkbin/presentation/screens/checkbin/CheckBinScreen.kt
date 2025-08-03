package com.example.checkbin.presentation.screens.checkbin

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.checkbin.R
import com.example.checkbin.presentation.components.BinDataCard
import com.example.checkbin.presentation.components.LoadingCircle
import com.example.checkbin.presentation.model.LoadingState
import com.example.checkbin.presentation.theme.defaultDimensions
import com.example.checkbin.presentation.utils.IntentUtils.openBrowser
import com.example.checkbin.presentation.utils.IntentUtils.openMap
import com.example.checkbin.presentation.utils.IntentUtils.openPhone

/**
 * Экран для ввода и проверки BIN (Bank Identification Number).
 *
 * Отображает поле ввода BIN, кнопку для запуска проверки, а также
 * результат проверки в виде карточки с информацией о карте.
 * Включает обработку состояний загрузки, успешного ответа и ошибок.
 *
 * Также содержит кнопку для перехода к истории проверок.
 *
 * @param onHistoryClick обработчик нажатия на кнопку перехода к истории BIN.
 * @param modifier модификатор для настройки внешнего вида компонента.
 * @param viewModel ViewModel, управляющий состоянием экрана.
 */
@Composable
fun CheckBinScreen(
    onHistoryClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CheckBinScreenViewModel = hiltViewModel<CheckBinScreenViewModel>(),
) {
    val uiState = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(defaultDimensions.small)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = defaultDimensions.small)
        ) {
            Text(
                text = stringResource(id = R.string.text_input_bin),
                modifier = Modifier.padding(end = defaultDimensions.small)
            )
            TextField(
                value = uiState.binInput,
                onValueChange = { viewModel.updateBin(it) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Button(
            onClick = { viewModel.getBinInfo() },
            enabled = uiState.isButtonEnabled
        ) {
            Text(text = stringResource(id = R.string.button_check_bin))
        }
        uiState.loadingState.let { loadingState ->
            when (loadingState) {
                is LoadingState.Success -> {
                    uiState.binData?.let { binData ->
                        BinDataCard(
                            binData = uiState.binData,
                            onPhoneClick = {
                                binData.bank.phone?.let { context.openPhone(number = it) }
                            },
                            onCityClick = {
                                if (binData.country.latitude != null && binData.country.longitude != null) {
                                    context.openMap(
                                        latitude = binData.country.latitude.toInt(),
                                        longitude = binData.country.longitude.toInt()
                                    )
                                }
                            },
                            onSiteClick = {
                                binData.bank.url?.let { context.openBrowser(link = it) }
                            }
                        )
                    }
                }

                is LoadingState.Loading -> {
                    LoadingCircle()
                }

                is LoadingState.Error -> {
                    Toast.makeText(context, loadingState.message, Toast.LENGTH_LONG).show()
                    viewModel.removeErrorState()
                }

                else -> { /* Nothing */
                }
            }
        }
    }

    HistoryFloatingButton(onClick = onHistoryClick)
}


/**
 * Плавающая кнопка для перехода к экрану истории проверок BIN.
 *
 * Размещается в правом нижнем углу экрана.
 *
 * @param onClick обработчик нажатия на кнопку.
 * @param modifier модификатор для настройки внешнего вида компонента.
 */
@Composable
private fun HistoryFloatingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = modifier
            .fillMaxSize()
            .padding(defaultDimensions.medium)
    ) {
        FloatingActionButton(onClick = { onClick() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = stringResource(id = R.string.content_history)
            )
        }
    }
}