package com.raulbarca.portfolio.core.network

import com.raulbarca.portfolio.core.model.PortfolioData
import com.raulbarca.portfolio.core.model.ResumeData

/**
 * Interface representing network calls to Portfolio API
 */
interface PortfolioNetworkDataSource {
    suspend fun getPortfolioOverview(): PortfolioData
    suspend fun getResume(): ResumeData
}