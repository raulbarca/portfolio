package com.raulbarca.portfolio.core.network.ktor

import com.raulbarca.portfolio.BuildConfig
import com.raulbarca.portfolio.core.model.PortfolioData
import com.raulbarca.portfolio.core.model.ResumeData
import com.raulbarca.portfolio.core.network.PortfolioNetworkDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

const val API_HOST = BuildConfig.API_HOST

class KtorPortfolioNetwork(
    private val client: HttpClient,
) : PortfolioNetworkDataSource {

    override suspend fun getPortfolioOverview(): PortfolioData =
        client.get("$API_HOST/overview").body<PortfolioData>()

    override suspend fun getResume(): ResumeData =
        client.get("$API_HOST/resume").body<ResumeData>()
}