package com.raulbarca.portfolio.feature.overview.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.raulbarca.portfolio.core.designsystem.PortfolioDestination
import com.raulbarca.portfolio.core.designsystem.theme.AppThemeUiMode
import com.raulbarca.portfolio.core.localization.locale.AppLocale
import com.raulbarca.portfolio.feature.overview.overview.OverviewScreenRoute
import com.raulbarca.portfolio.feature.overview.overview.OverviewViewModel
import com.raulbarca.portfolio.feature.overview.resume.ResumeScreenRoute
import org.koin.compose.viewmodel.koinViewModel

/**
 * Defines the navigation graph for the home feature.
 */
fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
    onUiModeChange: (AppThemeUiMode) -> Unit,
    onAppLocaleChange: (AppLocale) -> Unit,
) {
    navigation(
        route = PortfolioDestination.OverviewFeature.route,
        startDestination = PortfolioDestination.OverviewHomeScreen.route
    ) {
        composable(PortfolioDestination.OverviewHomeScreen.route) { entry ->
            val viewModel = entry.sharedViewModel<OverviewViewModel>(navController)
            OverviewScreenRoute(
                viewModel = viewModel,
                onUiModeChange = onUiModeChange,
                onAppLocaleChange = onAppLocaleChange,
                onResumeClick = {
                    navController.navigate(PortfolioDestination.OverviewResumeScreen.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(PortfolioDestination.OverviewResumeScreen.route) {
            ResumeScreenRoute(
                onBackClick = navController::popBackStack,
            )
        }
    }
}

/**
 * Returns a [ViewModel] scoped to the parent of the current [NavBackStackEntry].
 * This is useful when you want to share a ViewModel between multiple destinations in a navigation graph.
 */
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}