package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
data class SkiResortInfo(
	val resortId: Long,
	val resortName: String,
	val resortWebKey: SkiResortWebKey,
	val status: String,
	val openSlopeCount: Int,
	val currentWeather: CurrentWeather,
	val weeklyWeather: List<DailyWeather>
) {
	data class CurrentWeather(
		val temperature: Int,
		val condition: WeatherCondition,
	)

	data class DailyWeather(
		val day: String,
		val maxTemperature: Int,
		val minTemperature: Int,
		val condition: WeatherCondition,
	)
}