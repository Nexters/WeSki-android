package com.dieski.domain.network

import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

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

val NetworkResult<Any>.isUnexpectedException: Boolean
	get() = this is NetworkResult.Failure.UnexpectedException

val NetworkResult<Any>.isUnKnownError: Boolean
	get() = this is NetworkResult.Failure.UnknownError

val NetworkResult<Any>.messageOrNull: String?
	get() =
		when (this) {
			is NetworkResult.Failure -> throwable.message
			is NetworkResult.Success -> null
		}

@OptIn(ExperimentalContracts::class)
inline fun <T : Any> NetworkResult<T>.onSuccess(crossinline onResult: NetworkResult.Success<T>.() -> Unit): NetworkResult<T> {
	contract { callsInPlace(onResult, InvocationKind.AT_MOST_ONCE) }
	if (this is NetworkResult.Success) {
		onResult(this)
	}
	return this
}

@OptIn(ExperimentalContracts::class)
inline fun <T : Any> NetworkResult<T>.onFailure(crossinline onResult: NetworkResult.Failure.() -> Unit): NetworkResult<T> {
	contract { callsInPlace(onResult, InvocationKind.AT_MOST_ONCE) }
	if (this is NetworkResult.Failure) {
		onResult(this)
	}
	return this
}

@OptIn(ExperimentalContracts::class)
inline fun <T : Any> NetworkResult<T>.onHttpException(crossinline onResult: NetworkResult.Failure.HttpException.() -> Unit): NetworkResult<T> {
	contract { callsInPlace(onResult, InvocationKind.AT_MOST_ONCE) }
	if (this is NetworkResult.Failure.HttpException) {
		onResult(this)
	}
	return this
}

@OptIn(ExperimentalContracts::class)
inline fun <T : Any> NetworkResult<T>.onNetworkException(
	crossinline onResult: NetworkResult.Failure.UnexpectedException.() -> Unit,
): NetworkResult<T> {
	contract { callsInPlace(onResult, InvocationKind.AT_MOST_ONCE) }
	if (this is NetworkResult.Failure.UnexpectedException) {
		onResult(this)
	}
	return this
}
