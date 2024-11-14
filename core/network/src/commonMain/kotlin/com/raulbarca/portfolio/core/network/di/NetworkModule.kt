package com.raulbarca.portfolio.core.network.di

import com.raulbarca.portfolio.core.localization.locale.CurrentAppLocale
import com.raulbarca.portfolio.core.network.PortfolioNetworkDataSource
import com.raulbarca.portfolio.core.network.ktor.API_HOST
import com.raulbarca.portfolio.core.network.ktor.KtorPortfolioNetwork
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single<PortfolioNetworkDataSource> {
        KtorPortfolioNetwork(
            client = get(named(com.raulbarca.portfolio.BuildConfig.APP_NAME)),
        )
    }
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            explicitNulls = false
            prettyPrint = true
        }
    }
    single(named(com.raulbarca.portfolio.BuildConfig.APP_NAME)) {
        HttpClient {
            install(HttpTimeout) {
                requestTimeoutMillis = 30000L
            }
            install(ContentNegotiation) {
                json(get())
            }
            install(HttpCache)
            install(Logging) {
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = API_HOST
                }
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header(HttpHeaders.ContentLanguage, CurrentAppLocale.isoFormat)
            }
        }
    }
}