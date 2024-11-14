package com.raulbarca.portfolio.feature.overview.resume

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raulbarca.portfolio.core.common.Either
import com.raulbarca.portfolio.core.domain.usecase.GetResumeUseCase
import com.raulbarca.portfolio.feature.overview.resume.model.ResumeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ResumeViewModel(
    private val getResumeUseCase: GetResumeUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<ResumeUiState>(ResumeUiState.Loading)
    val uiState: StateFlow<ResumeUiState> = _uiState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ResumeUiState.Loading
        )

    fun load() {
        viewModelScope.launch {
            val resumeData = getResumeUseCase()
            _uiState.update {
                when (resumeData) {
                    is Either.Left -> ResumeUiState.Error(resumeData.value)
                    is Either.Right -> ResumeUiState.Success(resumeData.value)
                }
            }
        }
    }
}