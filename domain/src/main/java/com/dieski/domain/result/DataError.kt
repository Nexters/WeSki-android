package com.dieski.domain.result

/**
 *
 * @author   JGeun
 * @created  2024/09/16
 */
sealed interface DataError : WError {
	sealed interface Network : DataError {
		data object Redirect : Network
		data object ClientError : Network
		data object ServerError : Network
		data object TimeoutError : Network
		data object UnexpectedError : Network
		data object UnknownError : Network
	}

	sealed interface Local : DataError {
		data object DiskFullError : Local
	}
}