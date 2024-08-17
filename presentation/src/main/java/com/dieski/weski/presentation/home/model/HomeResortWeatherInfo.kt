package com.dieski.weski.presentation.home.model

import com.dieski.domain.model.ResortWeatherInfo
import com.dieski.domain.model.ResortWeatherInfo.ResortDailyWeatherInfo
import com.dieski.weski.presentation.core.model.WeatherType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import javax.annotation.concurrent.Immutable

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
@Immutable
data class HomeResortWeatherInfo(
	val name: String,
	val operatingSlopeCount: Int,
	val currentTemperature: Int,
	val weatherDescription: String,
	val weatherType: WeatherType,
	val weekWeatherInfo: ImmutableList<ResortDailyWeatherInfo>
)

fun ResortWeatherInfo.toUiModel() = HomeResortWeatherInfo(
	name = this.resortName,
	operatingSlopeCount = this.operatingSlopeCount,
	currentTemperature = this.currentTemperature,
	weatherDescription = this.weatherDescription,
	weatherType = WeatherType.findByName(weatherType),
	weekWeatherInfo = this.resortDailyWeatherInfoList.toPersistentList()
)