package com.raulbarca.portfolio.feature.overview.overview.model

import com.raulbarca.portfolio.core.model.PortfolioData

sealed interface OverviewUiState {
    data class Error(val message: String) : OverviewUiState
    data object Loading : OverviewUiState
    data class Success(val data: PortfolioData) : OverviewUiState
}