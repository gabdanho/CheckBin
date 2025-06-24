package com.example.checkbin.presentation.components

import android.content.Context
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.checkbin.data.remote.model.BinDataRequest

@Composable
fun BinInfoCard(
    context: Context,
    binInfo: BinDataRequest,
    onPhoneClick: (Context, String) -> Unit,
    onCityClick: (Context, Int, Int) -> Unit,
    onSiteClick: (Context, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.padding(8.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Scheme and prepaid
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("SCHEME", fontWeight = FontWeight.Bold)
                    Text(
                        text = binInfo.scheme ?: "unknown",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("PREPAID", fontWeight = FontWeight.Bold)
                    Text(
                        text = when(binInfo.prepaid) {
                            true -> "YES"
                            false -> "NO"
                            null -> "unknown"
                        },
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            // Brand and country
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("BRAND", fontWeight = FontWeight.Bold)
                    Text(
                        text = binInfo.brand ?: "unknown",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("COUNTRY", fontWeight = FontWeight.Bold)
                    Text(
                        text = "${binInfo.country?.emoji ?: ""} ${binInfo.country?.name ?: "unknown"}",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            // Card and type
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            )  {
                Column(modifier = Modifier.weight(1f)) {
                    Text("CARD NUMBER", fontWeight = FontWeight.Bold)
                    Text(
                        text = "LENGTH: ${binInfo.number?.length ?: "unknown"}; LUHN: ${
                            when(binInfo.number?.luhn) {
                                true -> "YES"
                                false -> "NO"
                                null -> "unknown"
                            }
                        }",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("TYPE", fontWeight = FontWeight.Bold)
                    Text(
                        text = if (binInfo.type == "debit") "Debit" else "Credit",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
        // Bank
        Column(modifier = Modifier.padding(8.dp)) {
            Text("BANK", fontWeight = FontWeight.Bold)
            Text(
                "Bank: ${binInfo.bank?.name ?: "unknown"}, city: ${binInfo.bank?.city ?: "unknown"}",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clickable { if (binInfo.country?.latitude != null && binInfo.country.longitude != null) onCityClick(context, binInfo.country.latitude, binInfo.country.longitude) }
            )
            Text(
                "URL: ${binInfo.bank?.url ?: "unknown"}",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clickable { if (binInfo.bank?.url != null) onSiteClick(context, binInfo.bank.url) }
            )
            Text(
                "Phone: ${binInfo.bank?.phone ?: "unknown"}",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { if (binInfo.bank?.phone != null) onPhoneClick(context, binInfo.bank.phone) }
            )
        }
    }
}

//@Preview
//@Composable
//fun BinInfoCardPreview() {
//    BinInfoCard(
//        modifier = Modifier.fillMaxWidth(),
//        binInfo = BinInfo(
//            number = NumberInfo(16, true),
//            scheme = "visa",
//            type = "debit",
//            brand = "Visa/Dankort",
//            prepaid = true,
//            country = Country("Denmark", "52"),
//            bank = Bank("Sber", "https://sber.ru", "800", "Moscow")
//        ),
//        onPhoneClick = { },
//        onCityClick = { _, _ -> },
//        onSiteClick = { }
//    )
//}