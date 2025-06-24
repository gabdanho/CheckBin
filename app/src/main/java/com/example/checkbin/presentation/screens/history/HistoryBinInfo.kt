package com.example.checkbin.presentation.screens.history

import android.content.Context
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.checkbin.presentation.components.BinInfoCard
import com.example.checkbin.data.remote.model.BinDataRequest

@Composable
fun HistoryBinInfo(
    modifier: Modifier = Modifier,
    context: Context,
    binInfoCards: List<BinDataRequest>?,
    onPhoneClick: (Context, String) -> Unit,
    onCityClick: (Context, Int, Int) -> Unit,
    onSiteClick: (Context, String) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = { TopBar(onBackClick = onBackClick) }
    ) { innerPadding ->
        if (!binInfoCards.isNullOrEmpty()) {
            LazyColumn(modifier = modifier
                .padding(innerPadding)
                .padding(8.dp)) {
                items(binInfoCards) {
                    BinInfoCard(
                        binInfo = it,
                        context = context,
                        onPhoneClick = onPhoneClick,
                        onCityClick = onCityClick,
                        onSiteClick = onSiteClick
                    )
                }
            }
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(8.dp)
            ) {
                Text("History not exists")
            }
        }
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    OutlinedButton(
        onClick = { onBackClick() },
        modifier = modifier.padding(8.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Go back"
        )
    }
}

//@Preview
//@Composable
//private fun HistoryBinInfoPreview() {
//    HistoryBinInfo(
//        modifier = Modifier, binInfoCards = listOf(FakeBinInfoCard.binInfoCard, FakeBinInfoCard.binInfoCard), { }, { _, _ -> }, { }, { }
//    )
//}