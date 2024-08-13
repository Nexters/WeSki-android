package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
data class ResortDailyWeatherInfo(
	val day: String,
	val weatherType: String,
	val avgTemperature: Int,
	val minTemperature: Int,
)
