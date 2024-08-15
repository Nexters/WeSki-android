package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/08/15
 */
data class TodayForecast(
	val currentTemperature: Int,
	val perceivedTemperature: Int,
	val weatherDescription: String,
	val highestTemperature: Int,
	val lowestTemperature: Int,
	val hourlyForecastWeatherInfoList: List<HourlyForecastWeatherInfo>
) {
	data class HourlyForecastWeatherInfo(
		val time: String,
		val weatherType: String,
		val temperature: Int,
		val chanceOfRain: Int,
	)
}