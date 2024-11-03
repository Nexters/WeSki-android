package com.dieski.domain.model

data class SkiResortWeatherInfo(
    val resortId: Int,
    val currentWeather: CurrentWeather,
    val todayWeatherByTime: List<HourlyWeather>,
    val weeklyWeather: List<DailyWeather>
) {
    data class CurrentWeather(
        val temperature: Int,
        val maxTemperature: Int,
        val minTemperature: Int,
        val feelsLike: Int,
        val description: String,
        val condition: WeatherCondition
    )

    data class HourlyWeather(
        val forecastTime: String,
        val temperature: Int,
        val precipitationChance: String,
        val condition: String,
    )

    data class DailyWeather(
        val day: String,
        val date: String,
        val precipitationChance: String,
        val maxTemperature: Int,
        val minTemperature: Int,
        val condition: String
    )
}