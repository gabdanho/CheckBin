package com.example.checkbin

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.checkbin.ui.navigation.BinNavGraph
import com.example.checkbin.ui.theme.CheckBinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheckBinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BinNavGraph(navController = rememberNavController(), context = this)
                }
            }
        }
    }
}

fun openPhone(context: Context, number: String) {
    val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel: $number"))
    context.startActivity(intent)
}

fun openMap(context: Context, latitude: Int , longitude: Int) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$latitude,$longitude"))
    context.startActivity(intent)
}

fun openBrowser(context: Context, link: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    context.startActivity(intent)
}