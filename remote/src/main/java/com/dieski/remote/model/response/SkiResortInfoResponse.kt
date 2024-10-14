package com.dieski.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author   JGeun
 * @created  2024/08/17
 */
@Serializable
data class SkiResortInfoResponse(
	@SerialName("resortId")
	val resortId: Long,
	@SerialName("name")
	val resortName: String,
	@SerialName("status")
	val status: String,
	@SerialName("openSlopes")
	val openSlopeCount: Int,
	@SerialName("currentWeather")
	val currentWeatherResponse: CurrentWeatherResponse,
	@SerialName("weeklyWeather")
	val weeklyWeatherResponse: List<DailyWeatherResponse>
) {
	@Serializable
	data class CurrentWeatherResponse(
		@SerialName("temperature")
		val temperature: Int,
		@SerialName("description")
		val description: String,
	)

	@Serializable
	data class DailyWeatherResponse(
		@SerialName("day")
		val day: String,
		@SerialName("maxTemperature")
		val maxTemperature: Int,
		@SerialName("minTemperature")
		val minTemperature: Int,
		@SerialName("description")
		val description: String,
	)
}
