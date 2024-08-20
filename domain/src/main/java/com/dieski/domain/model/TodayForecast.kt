package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/08/15
 */
data class TodayForecast(
	val currentTemperature: Int = 0,
	val perceivedTemperature: Int = 0,
	val weatherDescription: String = "",
	val highestTemperature: Int = 0,
	val lowestTemperature: Int = 0,
	val hourlyForecastWeatherInfoList: List<HourlyForecastWeatherInfo> = emptyList()
) {
	data class HourlyForecastWeatherInfo(
		val time: String = "",
		val weatherType: String = "",
		val temperature: Int = 0,
		val chanceOfRain: Int = 0,
	)
}