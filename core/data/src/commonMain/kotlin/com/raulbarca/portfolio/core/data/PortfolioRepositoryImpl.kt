package com.raulbarca.portfolio.core.data

import co.touchlab.kermit.Logger
import com.raulbarca.portfolio.core.common.ApiResponse
import com.raulbarca.portfolio.core.common.Either
import com.raulbarca.portfolio.core.model.PortfolioData
import com.raulbarca.portfolio.core.model.ResumeData
import com.raulbarca.portfolio.core.network.PortfolioNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.io.IOException

/**
 * Network backed implementation of the [PortfolioRepository].
 * @param portfolioNetworkDataSource The data source for the portfolio.
 * @param ioSDispatcher The dispatcher for the IO operations.
 */
class PortfolioRepositoryImpl(
    private val portfolioNetworkDataSource: PortfolioNetworkDataSource,
    private val ioSDispatcher: CoroutineDispatcher
) : PortfolioRepository {
    private val log = Logger.withTag(this::class.simpleName!!)

    override suspend fun getPortfolioOverview(): Either<ApiResponse, PortfolioData> =
        withContext(ioSDispatcher) {
            try {
                Either.Right(portfolioNetworkDataSource.getPortfolioOverview())
            } catch (e: IOException) {
                log.e(e) { "Error getting portfolio data" }
                Either.Left(ApiResponse.IOException)
            } catch (e: Exception) {
                log.e(e) { "Error getting portfolio data" }
                Either.Left(ApiResponse.HttpError)
            }
        }

    override suspend fun getResume(): Either<ApiResponse, ResumeData> =
        withContext(ioSDispatcher) {
            try {
                Either.Right(portfolioNetworkDataSource.getResume())
            } catch (e: IOException) {
                log.e(e) { "Error getting portfolio data" }
                Either.Left(ApiResponse.IOException)
            } catch (e: Exception) {
                log.e(e) { "Error getting portfolio data" }
                Either.Left(ApiResponse.HttpError)
            }
        }
}