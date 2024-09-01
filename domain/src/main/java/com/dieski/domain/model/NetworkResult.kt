package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/08/20
 */
sealed class NetworkResult<out T> {
	data class Success<T>(val data: T) : NetworkResult<T>()
	data class Error<T>(val code: Int, val message: String?) : NetworkResult<T>()
	data class Exception<T>(val t: Throwable) : NetworkResult<T>()
}

suspend fun <T: Any> NetworkResult<T>.onSuccess(
	executable: suspend (T) -> Unit
): NetworkResult<T> = apply {
	if (this is NetworkResult.Success<T>) {
		executable(data)
	}
}

suspend fun <T : Any> NetworkResult<T>.onFailure(
	executable: suspend (code: Int, message: String?) -> Unit
): NetworkResult<T> = apply {
	if (this is NetworkResult.Error<T>) {
		executable(code, message)
	}
}

suspend fun <T : Any> NetworkResult<T>.onException(
	executable: suspend (e: Throwable) -> Unit
): NetworkResult<T> = apply {
	if (this is NetworkResult.Exception<T>) {
		executable(t)
	}
}

fun <T, R> NetworkResult<T>.transform(onChange: (T) -> R): NetworkResult<R> {
	return when (this) {
		is NetworkResult.Exception -> NetworkResult.Exception(this.t)
		is NetworkResult.Error -> NetworkResult.Error(code = this.code, message = this.message)
		is NetworkResult.Success -> NetworkResult.Success(onChange(this.data))
	}
}