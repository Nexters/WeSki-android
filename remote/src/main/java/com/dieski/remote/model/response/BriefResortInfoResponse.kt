package com.dieski.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
@Serializable
data class BriefResortInfoResponse(
	@SerialName("key")
	val key: Int,
	@SerialName("name")
	val resortName: String,
	@SerialName("slopeNum")
	val operationSlopeCount: Int,
	@SerialName("weather")
	val weather: BriefResortWeatherInfoResponse
) {
	@Serializable
	data class BriefResortWeatherInfoResponse(
		@SerialName("description")
		val description: String,
		@SerialName("currTm")
		val temperature: Int
	)
}