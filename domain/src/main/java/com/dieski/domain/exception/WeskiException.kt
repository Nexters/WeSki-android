package com.dieski.domain.exception

/**
 *
 * @author   JGeun
 * @created  2024/09/13
 */
sealed class WeskiException(val error: Throwable) : RuntimeException(error)

class HttpException(code: Int, throwable: Throwable) : WeskiException(throwable) {
	init {
		require(code in ERROR_CODE_RANGE)
	}

	companion object {
		private val ERROR_CODE_RANGE = 100..599
	}
}

class NetworkException(throwable: Throwable) : WeskiException(throwable)

class UnknownException(throwable: Throwable) : WeskiException(throwable)