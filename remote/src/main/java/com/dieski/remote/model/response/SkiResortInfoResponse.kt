package com.dieski.remote.model.response

import com.dieski.data.model.ResortInfoDto
import com.dieski.remote.RemoteMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author   JGeun
 * @created  2024/08/17
 */
@Serializable
data class SkiResortInfoResponse(
	@SerialName("resortId")
	val resortId: Long,
	@SerialName("name")
	val resortName: String,
	@SerialName("status")
	val status: String,
	@SerialName("openSlopes")
	val openSlopeCount: Int,
	@SerialName("openingDate")
	val openingDate: String,
	@SerialName("currentWeather")
	val currentWeather: CurrentWeatherResponse,
	@SerialName("weeklyWeather")
	val weeklyWeather: List<DailyWeatherResponse>
) : RemoteMapper<ResortInfoDto> {

	override fun toData() = ResortInfoDto(
		resortId = resortId,
		resortName = resortName,
		status = status,
		openSlopeCount = openSlopeCount,
		openingDate = openingDate,
		currentWeather = currentWeather.toData(),
		weeklyWeather = weeklyWeather.map { it.toData() }
	)

	@Serializable
	data class CurrentWeatherResponse(
		@SerialName("temperature")
		val temperature: Int,
		@SerialName("description")
		val description: String,
	) : RemoteMapper<ResortInfoDto.CurrentWeatherDto> {
		override fun toData() = ResortInfoDto.CurrentWeatherDto(
			temperature = temperature,
			description = description
		)
	}

	@Serializable
	data class DailyWeatherResponse(
		@SerialName("day")
		val day: String,
		@SerialName("maxTemperature")
		val maxTemperature: Int,
		@SerialName("minTemperature")
		val minTemperature: Int,
		@SerialName("description")
		val description: String,
	) : RemoteMapper<ResortInfoDto.DailyWeatherDto> {
		override fun toData() = ResortInfoDto.DailyWeatherDto(
			day = day,
			maxTemperature = maxTemperature,
			minTemperature = minTemperature,
			description = description
		)
	}
}
