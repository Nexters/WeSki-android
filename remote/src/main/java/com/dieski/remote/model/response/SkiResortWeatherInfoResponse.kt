package com.dieski.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SkiResortWeatherInfoResponse(
    @SerialName("resortId")
    val resortId: Int,
    @SerialName("currentWeather")
    val currentWeather: CurrentWeather,
    @SerialName("hourlyWeather")
    val todayWeatherByTime: List<HourlyWeather>,
    @SerialName("weeklyWeather")
    val weeklyWeather: List<DailyWeather>
) {
    @Serializable
    data class CurrentWeather(
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
    )

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
    )

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
    )
}