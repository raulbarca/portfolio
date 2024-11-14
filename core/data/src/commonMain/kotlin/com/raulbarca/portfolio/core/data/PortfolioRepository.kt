package com.raulbarca.portfolio.core.data

import com.raulbarca.portfolio.core.common.ApiResponse
import com.raulbarca.portfolio.core.common.Either
import com.raulbarca.portfolio.core.model.PortfolioData
import com.raulbarca.portfolio.core.model.ResumeData

/**
 * Data layer interface for the portfolio
 */
interface PortfolioRepository {
    /**
     * Returns the portfolio overview data.
     */
    suspend fun getPortfolioOverview(): Either<ApiResponse, PortfolioData>

    /**
     * Returns the resumé data.
     */
    suspend fun getResume(): Either<ApiResponse, ResumeData>
}
