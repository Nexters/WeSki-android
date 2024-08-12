package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
data class Resort(
	val name: String,
	val currentSlopeCnt: Int,
	val temperature: Float,
	val weatherType: WeatherType,
	val weekWeatherInfo: List<ResortDailyWeatherInfo>
)