package com.raulbarca.portfolio.feature.overview.resume.model

import com.raulbarca.portfolio.core.model.ResumeData

sealed interface ResumeUiState {
    data class Error(val message: String) : ResumeUiState
    data object Loading : ResumeUiState
    data class Success(val data: ResumeData) : ResumeUiState
}