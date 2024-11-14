package com.raulbarca.portfolio.feature.overview.di

import com.raulbarca.portfolio.core.domain.di.domainModule
import com.raulbarca.portfolio.feature.overview.overview.OverviewViewModel
import com.raulbarca.portfolio.feature.overview.resume.ResumeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val overviewModule = module {
    includes(domainModule)
    viewModel { OverviewViewModel(get()) }
    viewModel { ResumeViewModel(get()) }
}