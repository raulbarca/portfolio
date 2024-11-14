package com.raulbarca.portfolio.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.raulbarca.portfolio.core.designsystem.PortfolioDestination
import com.raulbarca.portfolio.core.designsystem.theme.AppThemeUiMode
import com.raulbarca.portfolio.core.localization.locale.AppLocale
import com.raulbarca.portfolio.feature.overview.navigation.homeGraph

/**
 * Main navigation graph for the Portfolio app.
 * @param modifier Modifier to be applied to the NavHost.
 * @param startDestination The starting destination of the NavHost.
 * @param navController The NavController to be used by the NavHost.
 */
@Composable
fun PortfolioNavGraph(
    modifier: Modifier = Modifier,
    startDestination: PortfolioDestination = PortfolioDestination.OverviewFeature,
    navController: NavHostController = rememberNavController(),
    onUiModeChange: (AppThemeUiMode) -> Unit,
    onAppLocaleChange: (AppLocale) -> Unit,
) {
    NavHost(
        modifier = modifier,
        startDestination = startDestination.route,
        navController = navController,
    ) {
        homeGraph(
            navController = navController,
            onUiModeChange = onUiModeChange,
            onAppLocaleChange = onAppLocaleChange,
        )
    }
}