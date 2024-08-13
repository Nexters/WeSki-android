package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
data class ResortWeatherInfo(
	val name: String,
	val operatingSlopeCount: Int,
	val currentTemperature: Int,
	val weatherDescription: String,
	val weatherType: String,
	val weekWeatherInfo: List<ResortDailyWeatherInfo>
)