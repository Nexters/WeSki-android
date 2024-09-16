package com.dieski.domain.result

/**
 *
 * @author   JGeun
 * @created  2024/09/16
 */
sealed interface WResult<out D, out E: WError> {
	data class Success<out D, out E: WError> (val data: D) : WResult<D, E>
	data class Error<out D, out E: WError>(val error: E): WResult<D, E>
}