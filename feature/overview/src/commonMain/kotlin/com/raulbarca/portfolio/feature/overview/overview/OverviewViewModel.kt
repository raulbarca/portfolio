package com.raulbarca.portfolio.feature.overview.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raulbarca.portfolio.core.common.Either
import com.raulbarca.portfolio.core.domain.usecase.GetPortfolioOverviewUseCase
import com.raulbarca.portfolio.feature.overview.overview.model.OverviewUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OverviewViewModel(
    private val getPortfolioOverviewUseCase: GetPortfolioOverviewUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<OverviewUiState>(OverviewUiState.Loading)
    val uiState: StateFlow<OverviewUiState> = _uiState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = OverviewUiState.Loading
        )

    fun load() {
        viewModelScope.launch {
            val portfolioData = getPortfolioOverviewUseCase()
            _uiState.update {
                when (portfolioData) {
                    is Either.Left -> OverviewUiState.Error(portfolioData.value)
                    is Either.Right -> OverviewUiState.Success(portfolioData.value)
                }
            }
        }
    }
}