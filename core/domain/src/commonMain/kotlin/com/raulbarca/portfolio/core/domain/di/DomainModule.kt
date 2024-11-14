package com.raulbarca.portfolio.core.domain.di

import com.raulbarca.portfolio.core.data.di.dataModule
import com.raulbarca.portfolio.core.domain.usecase.GetPortfolioOverviewUseCase
import com.raulbarca.portfolio.core.domain.usecase.GetResumeUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    includes(dataModule)

    factoryOf(::GetPortfolioOverviewUseCase)
    factoryOf(::GetResumeUseCase)
}