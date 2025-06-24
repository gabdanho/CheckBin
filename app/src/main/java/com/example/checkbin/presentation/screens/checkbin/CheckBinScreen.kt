package com.example.checkbin.presentation.screens.checkbin

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.checkbin.presentation.components.BinInfoCard
import com.example.checkbin.presentation.model.LoadingState

@Composable
fun CheckBinScreen(
    onHistoryClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CheckBinScreenViewModel = viewModel<CheckBinScreenViewModel>()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = "Input BIN",
                modifier = Modifier.padding(end = 8.dp)
            )
            TextField(
                value = uiState.binInput,
                onValueChange = { viewModel.updateBin(it) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Button(
            onClick = { viewModel.getBinInfo() }
        ) {
            Text(text = "Check BIN")
        }
        with(uiState.loadingState) {
            when(this) {
                is LoadingState.Success -> { /* TODO : Показать BinInfoCard */ }
                is LoadingState.Loading -> { LoadingScreen() }
                is LoadingState.Error -> {
                    Toast.makeText(context, this.message, Toast.LENGTH_LONG).show()
                }
                else -> { /* Nothing */ }
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

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth().padding(8.dp)
    ) {
        CircularProgressIndicator()
    }
}

//@Preview
//@Composable
//private fun CheckBinScreenPreview() {
//    CheckBinScreen(modifier = Modifier, { }, { }, { _, _ -> }, { }, { })
//}