package com.dieski.domain.network

/**
 *
 * @author   JGeun
 * @created  2024/09/13
 */
val NetworkResult<Any>.isSuccess: Boolean
	get() = this is NetworkResult.Success

val NetworkResult<Any>.isFailure: Boolean
	get() = this is NetworkResult.Failure

val NetworkResult<Any>.isHttpException: Boolean
	get() = this is NetworkResult.Failure.HttpException

val NetworkResult<Any>.isNetworkException: Boolean
	get() = this is NetworkResult.Failure.NetworkException

val NetworkResult<Any>.isUnKnownError: Boolean
	get() = this is NetworkResult.Failure.UnknownError

val NetworkResult<Any>.messageOrNull: String?
	get() =
		when (this) {
			is NetworkResult.Failure -> throwable.message
			is NetworkResult.Success -> null
		}