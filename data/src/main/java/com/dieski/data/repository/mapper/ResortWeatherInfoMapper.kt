package com.dieski.data.repository.mapper

import com.dieski.remote.model.response.ResortWeatherInfoResponse
import com.dieski.domain.model.ResortWeatherInfo

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
fun ResortWeatherInfoResponse.toDomain() = ResortWeatherInfo(
	resortId = -1,
	resortName = this.resortName,
	resortWebKey = "",
	operatingSlopeCount = this.slopeNum,
	currentTemperature = 0,
	weatherDescription = "",
	weatherType = "snow",
	resortDailyWeatherInfoList = this.weather.map(ResortWeatherInfoResponse.ResortDailyWeatherInfoResponse::toDomain)
)

fun ResortWeatherInfoResponse.ResortDailyWeatherInfoResponse.toDomain() = ResortWeatherInfo.ResortDailyWeatherInfo(
	day = this.dayName,
	weatherType = this.name,
	maxTemperature = this.maxTemperature,
	minTemperature = this.minTemperature,
)