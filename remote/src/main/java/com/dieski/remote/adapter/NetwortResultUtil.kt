package com.dieski.remote.adapter

import com.dieski.domain.network.NetworkResult
import retrofit2.Response
import java.io.IOException

internal fun <T> Response<T>.toNetworkResult(): NetworkResult<T> {
	val code: Int = code()
	val error: String? = errorBody()?.string()
	return if (isSuccessful) { // 200..300
		val body = body()
		if (body != null) {
			NetworkResult.Success(body)
		} else {
			NetworkResult.Failure.UnknownError(IllegalStateException("body == null"))
		}
	} else { // 300 ~ 500번대 에러들
		NetworkResult.Failure.HttpException(code, IOException(error))
	}
}

internal fun <T> Throwable.toErrorResult(): NetworkResult<T> =
	when (this) {
		// Network Error - ex) UnknownHostException, SocketTimeoutException, ConnectException
		is IOException -> NetworkResult.Failure.UnexpectedException(this)
		// 통신 이슈 외 - ex) JsonSyntaxException, IllegalStateException
		else -> NetworkResult.Failure.UnknownError(this)
	}