package com.dieski.data.model

import com.dieski.data.DataMapper
import com.dieski.domain.model.SkiResortWeatherInfo
import com.dieski.domain.model.WeatherCondition

/**
 *
 * @author   JGeun
 * @created  2025/03/05
 */
data class ResortWeatherInfoDto(
	val resortId: Int,
	val currentWeather: CurrentWeatherDto,
	val todayWeatherByTime: List<HourlyWeatherDto>,
	val weeklyWeather: List<DailyWeatherDto>
) : DataMapper<SkiResortWeatherInfo> {

	override fun toDomain() = SkiResortWeatherInfo(
		resortId = this.resortId,
		currentWeather = this.currentWeather.toDomain(),
		todayWeatherByTime = this.todayWeatherByTime.map { it.toDomain() },
		weeklyWeather = this.weeklyWeather.map { it.toDomain() }
	)

	data class CurrentWeatherDto(
		val temperature: Int,
		val maxTemperature: Int,
		val minTemperature: Int,
		val feelsLike: Int,
		val description: String,
		val condition: String
	) : DataMapper<SkiResortWeatherInfo.CurrentWeather> {
		override fun toDomain() = SkiResortWeatherInfo.CurrentWeather(
			temperature = this.temperature,
			maxTemperature = this.maxTemperature,
			minTemperature = this.minTemperature,
			feelsLike = this.feelsLike,
			description = this.description,
			condition = WeatherCondition.findByKorean(this.condition)
		)
	}

	data class HourlyWeatherDto(
		// Define fields for hourly weather if needed
		val forecastTime: String, // Use String for DATETIME
		val temperature: Int,
		val precipitationChance: String,
		val condition: String,
	) : DataMapper<SkiResortWeatherInfo.HourlyWeather> {
		override fun toDomain() = SkiResortWeatherInfo.HourlyWeather(
			forecastTime = this.forecastTime,
			temperature = this.temperature,
			precipitationChance = this.precipitationChance,
			condition = this.condition,
		)
	}

	data class DailyWeatherDto(
		val day: String,
		val date: String,
		val precipitationChance: String,
		val maxTemperature: Int,
		val minTemperature: Int,
		val condition: String
	) : DataMapper<SkiResortWeatherInfo.DailyWeather> {
		override fun toDomain() = SkiResortWeatherInfo.DailyWeather(
			day = this.day,
			date = this.date,
			precipitationChance = this.precipitationChance,
			maxTemperature = this.maxTemperature,
			minTemperature = this.minTemperature,
			condition = this.condition
		)
	}
}