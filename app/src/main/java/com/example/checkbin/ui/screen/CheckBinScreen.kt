package com.example.checkbin.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.checkbin.model.fake.FakeBinInfoCard
import com.example.checkbin.ui.BinInfoCard
import com.example.whatbin.model.BinInfo

@Composable
fun CheckBinScreen(
    modifier: Modifier = Modifier,
    context: Context,
    binInfo: BinInfo?,
    isLoading: Boolean,
    onCheckBinClick: (String) -> Unit,
    onPhoneClick: (Context, String) -> Unit,
    onCityClick: (Context, Int, Int) -> Unit,
    onSiteClick: (Context, String) -> Unit,
    onHistoryClick: () -> Unit
) {
    var binInput by remember { mutableStateOf("") }
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(
                text = "Input BIN",
                modifier = Modifier.padding(end = 8.dp)
            )
            TextField(
                value = binInput,
                onValueChange = { binInput = it },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Button(
            onClick = { onCheckBinClick(binInput) }
        ) {
            Text(text = "Check BIN")
        }
        when {
            isLoading ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    CircularProgressIndicator()
                }
            (binInfo != null) -> {
                BinInfoCard(
                    binInfo = binInfo,
                    context = context,
                    onPhoneClick = onPhoneClick,
                    onCityClick = onCityClick,
                    onSiteClick = onSiteClick
                )
            }
        }
    }

    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        FloatingActionButton(onClick = { onHistoryClick() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = "History"
            )
        }
    }
}

//@Preview
//@Composable
//private fun CheckBinScreenPreview() {
//    CheckBinScreen(modifier = Modifier, { }, { }, { _, _ -> }, { }, { })
//}