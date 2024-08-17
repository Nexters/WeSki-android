package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
data class ResortWeatherInfo(
	val resortName: String,
	val operatingSlopeCount: Int,
	val currentTemperature: Int,
	val weatherDescription: String,
	val weatherType: String,
	val resortDailyWeatherInfoList: List<ResortDailyWeatherInfo>
) {
	data class ResortDailyWeatherInfo(
		val day: String,
		val weatherType: String,
		val maxTemperature: Int,
		val minTemperature: Int,
	)
}