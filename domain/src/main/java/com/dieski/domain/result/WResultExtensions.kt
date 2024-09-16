package com.dieski.domain.result

import com.dieski.domain.network.NetworkResult
import com.dieski.domain.network.isSuccess
import java.net.SocketTimeoutException
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 *
 * @author   JGeun
 * @created  2024/09/16
 */
inline fun <T, R> NetworkResult<T>.toResult(transform: (T) -> R): WResult<R, DataError> {
	return when (this) {
		is NetworkResult.Success -> WResult.Success(transform(this.data))
		is NetworkResult.Failure -> {
			when (this) {
				is NetworkResult.Failure.HttpException -> {
					when (this.code) {
						in 300..399 -> WResult.Error(DataError.Network.Redirect)
						in 400..499 -> WResult.Error(DataError.Network.ClientError)
						in 500..599 -> WResult.Error(DataError.Network.ServerError)
						else -> WResult.Error(DataError.Network.UnknownError)
					}
				}
				is NetworkResult.Failure.UnexpectedException -> {
					when (this.error) {
						is SocketTimeoutException -> WResult.Error(DataError.Network.TimeoutError)
						else -> WResult.Error(DataError.Network.UnexpectedError)
					}
				}
				is NetworkResult.Failure.UnknownError -> WResult.Error(DataError.Network.UnknownError)
			}
		}
	}
}

val WResult<Any, WError>.isSuccess: Boolean
	get() = this is WResult.Success

val WResult<Any, WError>.isError: Boolean
	get() = this is WResult.Error

@OptIn(ExperimentalContracts::class)
inline fun <T : Any, D: WError> WResult<T, D>.onSuccess(crossinline onResult: WResult.Success<T, D>.() -> Unit): WResult<T, D> {
	contract { callsInPlace(onResult, InvocationKind.AT_MOST_ONCE) }
	if (this is WResult.Success) {
		onResult(this)
	}
	return this
}

@OptIn(ExperimentalContracts::class)
inline fun <T : Any, D: WError> WResult<T, D>.onError(crossinline onResult: WResult.Error<T, D>.() -> Unit): WResult<T, D> {
	contract { callsInPlace(onResult, InvocationKind.AT_MOST_ONCE) }
	if (this is WResult.Error) {
		onResult(this)
	}
	return this
}