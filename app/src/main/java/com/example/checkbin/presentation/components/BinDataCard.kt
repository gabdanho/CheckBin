package com.example.checkbin.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.example.checkbin.R
import com.example.checkbin.presentation.model.BinData
import com.example.checkbin.presentation.theme.defaultDimensions
import com.example.checkbin.presentation.utils.StringResUtils.booleanToStringRes

/**
 * Компонент карточки с информацией о BIN.
 *
 * Отображает сведения о платёжной карте.
 *
 * @param binData объект [BinData], содержащий все данные для отображения.
 * @param onPhoneClick обработчик клика по номеру телефона банка.
 * @param onCityClick обработчик клика по названию города (например, для открытия карты).
 * @param onSiteClick обработчик клика по URL сайта банка.
 * @param modifier модификатор компоновки (по умолчанию — [Modifier]).
 */
@Composable
fun BinDataCard(
    binData: BinData,
    onPhoneClick: () -> Unit,
    onCityClick: () -> Unit,
    onSiteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.padding(defaultDimensions.small)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(defaultDimensions.small)
        ) {
            // Scheme and prepaid
            InfoBlock(
                firstPart = PartBlockInfo(
                    title = stringResource(id = R.string.text_scheme),
                    info = binData.scheme ?: stringResource(id = R.string.text_nothing)
                ),
                secondPart = PartBlockInfo(
                    title = stringResource(id = R.string.text_prepaid),
                    info = stringResource(id = booleanToStringRes(binData.prepaid))
                )
            )
            // Brand and country
            InfoBlock(
                firstPart = PartBlockInfo(
                    title = stringResource(id = R.string.text_brand),
                    info = binData.brand ?: stringResource(id = R.string.text_nothing)
                ),
                secondPart = PartBlockInfo(
                    title = stringResource(id = R.string.text_country),
                    info = stringResource(
                        id = R.string.text_emoji_and_country,
                        binData.country.emoji ?: stringResource(id = R.string.text_nothing),
                        binData.country.name ?: stringResource(id = R.string.text_nothing)
                    )
                )
            )
            // Card and type
            InfoBlock(
                firstPart = PartBlockInfo(
                    title = stringResource(id = R.string.text_card_number),
                    info = stringResource(
                        id = R.string.text_length_luhn,
                        binData.number.length ?: stringResource(id = R.string.text_nothing),
                        stringResource(id = booleanToStringRes(binData.number.luhn))
                    )
                ),
                secondPart = PartBlockInfo(
                    title = stringResource(id = R.string.text_type),
                    info = binData.type ?: stringResource(id = R.string.text_nothing)
                )
            )
        }
        // Bank
        Column(modifier = Modifier.padding(defaultDimensions.small)) {
            Text(stringResource(id = R.string.text_bank), fontWeight = FontWeight.Bold)
            Text(
                stringResource(
                    id = R.string.text_bank_city,
                    binData.bank.name ?: stringResource(id = R.string.text_nothing),
                    binData.bank.city ?: stringResource(id = R.string.text_nothing)
                ),
                style = MaterialTheme.typography.labelMedium,
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(bottom = defaultDimensions.small)
                    .clickable {
                        if (binData.country.latitude != null && binData.country.longitude != null) onCityClick()
                    }
            )
            Text(
                stringResource(
                    id = R.string.text_url,
                    binData.bank.url ?: stringResource(id = R.string.text_nothing)
                ),
                style = MaterialTheme.typography.labelMedium,
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(bottom = defaultDimensions.small)
                    .clickable { if (binData.bank.url != null) onSiteClick() }
            )
            Text(
                stringResource(
                    id = R.string.text_phone,
                    binData.bank.phone ?: stringResource(id = R.string.text_nothing)
                ),
                style = MaterialTheme.typography.labelMedium,
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    if (binData.bank.phone != null) onPhoneClick()
                }
            )
        }
    }
}

/**
 * Компонент для отображения пары информационных блоков в одной строке.
 *
 * Используется для компактного отображения двух пар "заголовок-значение".
 *
 * @param modifier модификатор для настройки внешнего вида и позиционирования.
 * @param firstPart первая пара [PartBlockInfo] (слева).
 * @param secondPart вторая пара [PartBlockInfo] (справа).
 */
@Composable
private fun InfoBlock(
    modifier: Modifier = Modifier,
    firstPart: PartBlockInfo,
    secondPart: PartBlockInfo
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = defaultDimensions.small)
    ) {
        Column(modifier = Modifier.weight(defaultDimensions.fullWeight)) {
            Text(text = firstPart.title, fontWeight = FontWeight.Bold)
            Text(
                text = firstPart.info,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.weight(defaultDimensions.fullWeight)
        ) {
            Text(text = secondPart.title, fontWeight = FontWeight.Bold)
            Text(text = secondPart.info)
        }
    }
}


/**
 * Модель данных для одной части информационного блока.
 *
 * Используется в [InfoBlock] для отображения заголовка и соответствующего значения.
 *
 * @property title заголовок поля.
 * @property info значение поля.
 */
private data class PartBlockInfo(
    val title: String,
    val info: String
)