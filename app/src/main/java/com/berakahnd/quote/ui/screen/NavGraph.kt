package com.berakahnd.quote.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.berakahnd.quote.ui.viewmodel.MainViewModel

@Composable
fun AppNavHost(
    viewModel: MainViewModel ,
    navController: NavHostController,
    startDestination: String = NavigationItem.Main.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Main.route) {
            MainScreen(
                quoteItem = viewModel.uistate.value.quoteItem,
                onRemoteQuoteClick = {
                    viewModel.getRemoteQuote()
                },
                onShowListQuoteClick = {
                    navController.navigate(Screen.DETAILS.name)
                }
            )
        }
        composable(NavigationItem.Details.route) {
            DetailsScreen(
                listQuoteItem = viewModel.uistate.value.listQuoteItem,
                onDeleteQuoteClick = { quoteItem ->
                    viewModel.delete(quoteItem)
                },
                onNavigateBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
enum class Screen {
    MAIN,
    DETAILS
}
sealed class NavigationItem(val route: String) {
    object Details : NavigationItem(Screen.DETAILS.name)
    object Main : NavigationItem(Screen.MAIN.name)
}