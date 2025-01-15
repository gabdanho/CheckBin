package com.example.checkbin.ui.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.checkbin.openBrowser
import com.example.checkbin.openMap
import com.example.checkbin.openPhone
import com.example.checkbin.ui.BinViewModel
import com.example.checkbin.ui.screen.CheckBinScreen
import com.example.checkbin.ui.screen.HistoryBinInfo

@Composable
fun BinNavGraph(
    modifier: Modifier = Modifier,
    context: Context,
    navController: NavHostController,
    viewModel: BinViewModel = viewModel<BinViewModel>()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.errorMessage) {
        if (uiState.errorMessage != null) {
            Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_LONG).show()
            viewModel.removeErrorMessage()
        }
    }

    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.MAIN.name,
        modifier = modifier
    ) {
        composable(route = NavigationRoutes.MAIN.name) {
            CheckBinScreen(
                context = context,
                binInfo = uiState.binInfo,
                isLoading = uiState.isLoading,
                onCheckBinClick = viewModel::getBinInfo,
                onHistoryClick = { navController.navigate(NavigationRoutes.HISTORY.name) },
                onCityClick = ::openMap,
                onPhoneClick = ::openPhone,
                onSiteClick = ::openBrowser
            )
        }

        composable(route = NavigationRoutes.HISTORY.name) {
            HistoryBinInfo(
                context = context,
                binInfoCards = uiState.binInfoCardsHistory,
                onBackClick = { navController.popBackStack() },
                onCityClick = ::openMap,
                onSiteClick = ::openBrowser,
                onPhoneClick = ::openPhone
            )
        }
    }
}