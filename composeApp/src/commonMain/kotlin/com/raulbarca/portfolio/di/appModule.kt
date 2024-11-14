package com.raulbarca.portfolio.di

import coil3.annotation.ExperimentalCoilApi
import coil3.network.CacheStrategy
import coil3.network.NetworkFetcher
import coil3.network.ktor3.asNetworkClient
import com.raulbarca.portfolio.feature.overview.di.overviewModule
import io.ktor.client.HttpClient
import org.koin.dsl.module

@OptIn(ExperimentalCoilApi::class)
val appModule = module {
    includes(overviewModule)
    single {
        NetworkFetcher.Factory(
            networkClient = { get<HttpClient>().asNetworkClient() },
            cacheStrategy = { CacheStrategy() },
        )
    }
}