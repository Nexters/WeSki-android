package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/08/20
 */
data class WeekWeatherInfo(
	val day: String,
	val date: String,
	val chanceOfRain: Int,
	val weatherType: String,
	val highestTemperature: Int,
	val lowestTemperature: Int,
)