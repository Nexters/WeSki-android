package com.dieski.data.exception

import com.dieski.domain.network.NetworkResult
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
fun <T> NetworkResult<T>.getOrNull(): T? {
	return when (this) {
		is NetworkResult.Success -> data
		is NetworkResult.Failure -> null
	}
}

fun <T> NetworkResult<T>.getOrElse(defaultValue: T): T {
	return when (this) {
		is NetworkResult.Success -> data
		is NetworkResult.Failure -> defaultValue
	}
}

inline fun <T> NetworkResult<T>.getOrElse(defaultValue: () -> T): T {
	return when (this) {
		is NetworkResult.Success -> data
		is NetworkResult.Failure -> defaultValue()
	}
}

fun <T> NetworkResult<T>.getOrThrow(): T {
	return when (this) {
		is NetworkResult.Success -> data
		is NetworkResult.Failure -> {
			when (this) {
				is NetworkResult.Failure.HttpException -> throw HttpException(code, throwable)
				is NetworkResult.Failure.NetworkException -> throw NetworkException(throwable)
				is NetworkResult.Failure.UnknownError -> throw UnknownException(throwable)
			}
		}
	}
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
	crossinline onResult: NetworkResult.Failure.NetworkException.() -> Unit,
): NetworkResult<T> {
	contract { callsInPlace(onResult, InvocationKind.AT_MOST_ONCE) }
	if (this is NetworkResult.Failure.NetworkException) {
		onResult(this)
	}
	return this
}
