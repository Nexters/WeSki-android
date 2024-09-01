package com.dieski.weski.presentation.detail.model

import androidx.compose.runtime.Immutable
import com.dieski.domain.model.ResortWeatherInfo
import com.dieski.weski.presentation.core.model.WeatherType

/**
 *
 * @author   JGeun
 * @created  2024/08/15
 */
@Immutable
data class ResortBriefData(
	val resortName: String,
	val operatingSlopeCount: Int,
	val temperature: Int,
	val weatherType: WeatherType,
	val weatherDescription: String,
)

fun ResortWeatherInfo.toUiModel() = ResortBriefData(
	resortName = resortName,
	operatingSlopeCount = operatingSlopeCount,
	temperature = currentTemperature,
	weatherType = WeatherType.valueOf(weatherType),
	weatherDescription = weatherDescription
)