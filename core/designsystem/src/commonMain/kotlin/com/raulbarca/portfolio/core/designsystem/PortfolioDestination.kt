package com.raulbarca.portfolio.core.designsystem

/**
 * Destinations used in the [PortfolioDestination].
 */
sealed class PortfolioDestination(val route: String) {
    data object OverviewFeature : PortfolioDestination("feature_overview")
    data object OverviewHomeScreen : PortfolioDestination("screen_overview_home")
    data object OverviewResumeScreen : PortfolioDestination("screen_overview_resume")
}