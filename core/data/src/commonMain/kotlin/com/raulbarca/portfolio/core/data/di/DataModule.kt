package com.raulbarca.portfolio.core.data.di

import com.raulbarca.portfolio.core.common.di.PortfolioDispatchers
import com.raulbarca.portfolio.core.common.di.commonModule
import com.raulbarca.portfolio.core.data.PortfolioRepository
import com.raulbarca.portfolio.core.data.PortfolioRepositoryImpl
import com.raulbarca.portfolio.core.network.di.networkModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(commonModule, networkModule)
    single<PortfolioRepository> {
        PortfolioRepositoryImpl(
            portfolioNetworkDataSource = get(),
            ioSDispatcher = get(named(PortfolioDispatchers.IO))
        )
    }
}