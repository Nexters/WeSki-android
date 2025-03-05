package com.dieski.remote.model.response

import com.dieski.data.model.ResortWeatherInfoDto
import com.dieski.remote.RemoteMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResortWeatherInfoResponse(
	@SerialName("resortId")
	val resortId: Int,
	@SerialName("currentWeather")
	val currentWeather: CurrentWeatherResponse,
	@SerialName("hourlyWeather")
	val todayWeatherByTime: List<HourlyWeather>,
	@SerialName("weeklyWeather")
	val weeklyWeather: List<DailyWeather>
) : RemoteMapper<ResortWeatherInfoDto> {

	override fun toData(): ResortWeatherInfoDto = ResortWeatherInfoDto(
		resortId = resortId,
		currentWeather = currentWeather.toData(),
		todayWeatherByTime = todayWeatherByTime.map { it.toData() },
		weeklyWeather = weeklyWeather.map { it.toData() }
	)

	@Serializable
	data class CurrentWeatherResponse(
		@SerialName("temperature")
		val temperature: Int,
		@SerialName("maxTemperature")
		val maxTemperature: Int,
		@SerialName("minTemperature")
		val minTemperature: Int,
		@SerialName("feelsLike")
		val feelsLike: Int,
		@SerialName("description")
		val description: String,
		@SerialName("condition")
		val condition: String
	) : RemoteMapper<ResortWeatherInfoDto.CurrentWeatherDto> {
		override fun toData() = ResortWeatherInfoDto.CurrentWeatherDto(
			temperature = temperature,
			maxTemperature = maxTemperature,
			minTemperature = minTemperature,
			feelsLike = feelsLike,
			description = description,
			condition = condition
		)
	}

	@Serializable
	data class HourlyWeather(
		// Define fields for hourly weather if needed
		@SerialName("time")
		val forecastTime: String, // Use String for DATETIME
		@SerialName("temperature")
		val temperature: Int,
		@SerialName("precipitationChance")
		val precipitationChance: String,
		@SerialName("condition")
		val condition: String,
	) : RemoteMapper<ResortWeatherInfoDto.HourlyWeatherDto> {
		override fun toData() = ResortWeatherInfoDto.HourlyWeatherDto(
			forecastTime = forecastTime,
			temperature = temperature,
			precipitationChance = precipitationChance,
			condition = condition
		)
	}

	@Serializable
	data class DailyWeather(
		@SerialName("day")
		val day: String,
		@SerialName("date")
		val date: String,
		@SerialName("precipitationChance")
		val precipitationChance: String,
		@SerialName("maxTemperature")
		val maxTemperature: Int,
		@SerialName("minTemperature")
		val minTemperature: Int,
		@SerialName("condition")
		val condition: String
	) : RemoteMapper<ResortWeatherInfoDto.DailyWeatherDto> {
		override fun toData() = ResortWeatherInfoDto.DailyWeatherDto(
			day = day,
			date = date,
			precipitationChance = precipitationChance,
			maxTemperature = maxTemperature,
			minTemperature = minTemperature,
			condition = condition
		)
	}
}