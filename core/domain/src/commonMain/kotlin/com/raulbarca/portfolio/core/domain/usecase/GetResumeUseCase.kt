package com.raulbarca.portfolio.core.domain.usecase

import com.raulbarca.portfolio.core.common.ApiResponse
import com.raulbarca.portfolio.core.common.Either
import com.raulbarca.portfolio.core.data.PortfolioRepository
import com.raulbarca.portfolio.core.model.ResumeData

/**
 * Use case which gets [ResumeData]s
 */
class GetResumeUseCase(
    private val portfolioRepository: PortfolioRepository
) {
    suspend operator fun invoke(): Either<String, ResumeData> =
        when (val data = portfolioRepository.getResume()) {
            is Either.Left -> {
                when (data.value) {
                    is ApiResponse.IOException -> Either.Left("Network unavailable")
                    is ApiResponse.HttpError -> Either.Left("Error getting portfolio, try again later")
                }
            }

            is Either.Right -> Either.Right(data.value)
        }
}