package com.example.checkbin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.checkbin.presentation.screens.checkbin.CheckBinScreen
import com.example.checkbin.presentation.screens.history.HistoryBinInfo

/**
 * Навигационный граф приложения, определяющий маршруты между экранами.
 *
 * Включает маршруты:
 * - [NavigationRoutes.MAIN] — экран проверки BIN.
 * - [NavigationRoutes.HISTORY] — экран истории запросов.
 *
 * @param modifier модификатор для настройки внешнего вида навигационного контейнера.
 * @param navController контроллер навигации, управляющий переходами между экранами.
 */
@Composable
fun BinNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.MAIN.name,
        modifier = modifier
    ) {

        composable(route = NavigationRoutes.MAIN.name) {
            CheckBinScreen(
                onHistoryClick = { navController.navigate(NavigationRoutes.HISTORY.name) },
            )
        }

        composable(route = NavigationRoutes.HISTORY.name) {
            HistoryBinInfo(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}