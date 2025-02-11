package com.dieski.data.repository.mapper

import com.dieski.remote.model.response.SkiResortInfoResponse
import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.WeatherCondition

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
fun SkiResortInfoResponse.toDomain() = SkiResortInfo(
	resortId = this.resortId,
	resortName = this.resortName,
	status = this.status,
	openSlopeCount = this.openSlopeCount,
	openingDate = this.openingDate,
	currentWeather = this.currentWeatherResponse.toDomain(),
	weeklyWeather = this.weeklyWeatherResponse.map { it.toDomain() }
)

fun SkiResortInfoResponse.CurrentWeatherResponse.toDomain() = SkiResortInfo.CurrentWeather(
	temperature = this.temperature,
	condition = WeatherCondition.findByKorean(this.description)
)

fun SkiResortInfoResponse.DailyWeatherResponse.toDomain() = SkiResortInfo.DailyWeather(
	day = this.day,
	maxTemperature = this.maxTemperature,
	minTemperature = this.minTemperature,
	condition = WeatherCondition.findByKorean(this.description)
)