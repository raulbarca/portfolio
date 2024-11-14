package com.raulbarca.portfolio.core.common.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule = module {
    single(named(PortfolioDispatchers.IO)) { provideIoDispatcher() }
    single(named(PortfolioDispatchers.Default)) { Dispatchers.Default }
    single<CoroutineScope> {
        provideApplicationScope(get(named(PortfolioDispatchers.Default)))
    }
}

expect fun provideIoDispatcher(): CoroutineDispatcher
fun provideApplicationScope(dispatcher: CoroutineDispatcher): CoroutineScope =
    CoroutineScope(SupervisorJob() + dispatcher)


enum class PortfolioDispatchers {
    IO, Default
}