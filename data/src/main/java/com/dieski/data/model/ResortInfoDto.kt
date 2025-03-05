package com.dieski.data.model

import com.dieski.data.DataMapper
import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.WeatherCondition

/**
 *
 * @author   JGeun
 * @created  2025/03/05
 */
data class ResortInfoDto(
	val resortId: Long,
	val resortName: String,
	val status: String,
	val openSlopeCount: Int,
	val openingDate: String,
	val currentWeather: CurrentWeatherDto,
	val weeklyWeather: List<DailyWeatherDto>
) : DataMapper<SkiResortInfo> {

	override fun toDomain(): SkiResortInfo {
		return SkiResortInfo(
			resortId = this.resortId,
			resortName = this.resortName,
			status = this.status,
			openSlopeCount = this.openSlopeCount,
			openingDate = this.openingDate,
			currentWeather = this.currentWeather.toDomain(),
			weeklyWeather = this.weeklyWeather.map { it.toDomain() }
		)
	}

	data class CurrentWeatherDto(
		val temperature: Int,
		val description: String,
	) : DataMapper<SkiResortInfo.CurrentWeather> {
		override fun toDomain() = SkiResortInfo.CurrentWeather(
			temperature = this.temperature,
			condition = WeatherCondition.findByKorean(this.description)
		)
	}

	data class DailyWeatherDto(
		val day: String,
		val maxTemperature: Int,
		val minTemperature: Int,
		val description: String,
	) : DataMapper<SkiResortInfo.DailyWeather> {
		override fun toDomain() = SkiResortInfo.DailyWeather(
			day = this.day,
			maxTemperature = this.maxTemperature,
			minTemperature = this.minTemperature,
			condition = WeatherCondition.findByKorean(this.description)
		)
	}
}
