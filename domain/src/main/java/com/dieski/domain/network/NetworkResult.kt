package com.dieski.domain.network

/**
 *
 * @author   JGeun
 * @created  2024/08/20
 */

/**
 * NetworkState is a sealed class that represents the state of a network request.
 * It can be one of the following:
 * - [Success] : 200..300 코드 사이의 응답
 * - [Failure] : 200 번대 외의 응답
 * - [Failure.HttpException] : 네트워크 에러 (서버와 관련된 에러)
 * - [Failure.UnexpectedException] : 알 수 없는 에러 (IOException, ConnectException, SocketTimeoutException 이외의 에러)
 */
sealed interface NetworkResult<out T> {
	data class Success<T>(val data: T) : NetworkResult<T>

	sealed class Failure(val throwable: Throwable) : NetworkResult<Nothing> {
		data class HttpException(val code: Int, val error: Throwable) : Failure(error)

		data class UnexpectedException(val error: Throwable) : Failure(error)

		data class UnknownError(val error: Throwable) : Failure(error)
	}
}