package com.dieski.data.remote.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author   JGeun
 * @created  2024/08/17
 */
@Serializable
data class ResortWeatherInfoResponse(
	@SerialName("key")
	val key: Int,
	@SerialName("name")
	val resortName: String,
	@SerialName("slopeNum")
	val slopeNum: Int,
	@SerialName("weather")
	val weather: List<ResortDailyWeatherInfoResponse>
) {
	@Serializable
	data class ResortDailyWeatherInfoResponse(
		@SerialName("dayName")
		val dayName: String,
		// 날씨 타입
		@SerialName("name")
		val name: String,
		@SerialName("maxTm")
		val maxTemperature: Int,
		@SerialName("minTm")
		val minTemperature: Int,
		@SerialName("perRain")
		val chanceOfRain: Int,
	)
}
