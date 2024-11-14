package com.raulbarca.portfolio.core.common

/**
 * A generic class to represent different types of data.
 * [Either.Left] Represents the left side of [Either] class which by convention is a "Failure".
 * [Either.Right] Represents the right side of [Either] class which by convention is a "Success".
 * @param A The type of the left side.
 * @param B The type of the right side.
 */
sealed class Either<out A, out B> {
    data class Left<out A>(val value: A) : Either<A, Nothing>()
    data class Right<out B>(val value: B) : Either<Nothing, B>()
}

/**
 * Sealed class to represent the different types of API responses.
 * [ApiResponse.IOException] Represents an IO exception.
 * [ApiResponse.HttpError] Represents an HTTP error.
 */
sealed interface ApiResponse {
    data object HttpError : ApiResponse
    data object IOException : ApiResponse
}
