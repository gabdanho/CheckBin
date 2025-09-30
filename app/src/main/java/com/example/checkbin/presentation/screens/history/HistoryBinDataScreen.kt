package com.example.checkbin.presentation.screens.history

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.checkbin.R
import com.example.checkbin.presentation.components.BinDataCard
import com.example.checkbin.presentation.model.LoadingState
import com.example.checkbin.presentation.theme.defaultDimensions
import com.example.checkbin.presentation.utils.IntentUtils.openBrowser
import com.example.checkbin.presentation.utils.IntentUtils.openMap
import com.example.checkbin.presentation.utils.IntentUtils.openPhone

/**
 * Экран отображения истории проверок BIN.
 *
 * Отображает список ранее проверенных BIN-данных в виде прокручиваемого списка.
 * Если история пуста, показывает соответствующее сообщение.
 *
 * @param onBackClick обработчик нажатия кнопки "назад".
 * @param modifier модификатор для настройки внешнего вида экрана.
 * @param viewModel ViewModel, управляющий состоянием истории (по умолчанию внедряется через Hilt).
 */
@Composable
fun HistoryBinDataScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HistoryBinDataScreenViewModel = hiltViewModel<HistoryBinDataScreenViewModel>()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    Scaffold(
        topBar = { TopBar(onBackClick = onBackClick) }
    ) { innerPadding ->
        uiState.loadingState?.let { loadingState ->
            when (loadingState) {
                is LoadingState.Success -> {
                    if (uiState.binHistory.isNotEmpty()) {
                        LazyColumn(
                            modifier = modifier
                                .padding(innerPadding)
                                .padding(defaultDimensions.small)
                        ) {
                            items(uiState.binHistory) { binData ->
                                BinDataCard(
                                    binData = binData,
                                    onPhoneClick = { binData.bank.phone?.let { context.openPhone(number = it) } },
                                    onCityClick = {
                                        binData.country.latitude?.toIntOrNull()?.let { latitude ->
                                            binData.country.longitude?.toIntOrNull()?.let { longitude ->
                                                context.openMap(
                                                    latitude = latitude,
                                                    longitude = longitude
                                                )
                                            }
                                        }
                                    },
                                    onSiteClick = {
                                        binData.bank.url?.let { context.openBrowser(link = it) }
                                    }
                                )
                            }
                        }
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(innerPadding)
                                .padding(defaultDimensions.small)
                        ) {
                            Text(stringResource(id = R.string.text_history_not_exists))
                        }
                    }
                }
                else -> { /* Nothing */ }
            }
        }
    }
}

/**
 * Верхняя панель с кнопкой "назад".
 *
 * Используется в экране истории для возврата к предыдущему экрану.
 *
 * @param onBackClick обработчик нажатия на кнопку "назад".
 * @param modifier модификатор для настройки внешнего вида компонента.
 */
@Composable
private fun TopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = { onBackClick() },
        modifier = modifier.padding(defaultDimensions.small)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.content_go_back)
        )
    }
}