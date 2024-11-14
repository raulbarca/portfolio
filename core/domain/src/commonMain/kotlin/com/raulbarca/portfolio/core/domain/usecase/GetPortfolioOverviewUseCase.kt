package com.raulbarca.portfolio.core.domain.usecase

import com.raulbarca.portfolio.core.common.ApiResponse
import com.raulbarca.portfolio.core.common.Either
import com.raulbarca.portfolio.core.data.PortfolioRepository
import com.raulbarca.portfolio.core.model.PortfolioData

/**
 * Use case which gets [PortfolioData]s
 */
class GetPortfolioOverviewUseCase(
    private val portfolioRepository: PortfolioRepository
) {
    suspend operator fun invoke(): Either<String, PortfolioData> =
        when (val data = portfolioRepository.getPortfolioOverview()) {
            is Either.Left -> {
                when (data.value) {
                    is ApiResponse.IOException -> Either.Left("Network unavailable")
                    is ApiResponse.HttpError -> Either.Left("Error getting portfolio, try again later")
                }
            }

            is Either.Right -> Either.Right(data.value)
        }
}