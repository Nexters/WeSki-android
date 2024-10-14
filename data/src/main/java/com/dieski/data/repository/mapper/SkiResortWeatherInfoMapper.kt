package com.dieski.data.repository.mapper

import com.dieski.domain.model.SkiResortWeatherInfo
import com.dieski.domain.model.WeatherCondition
import com.dieski.remote.model.response.SkiResortWeatherInfoResponse

fun SkiResortWeatherInfoResponse.toDomain() = SkiResortWeatherInfo(
    resortId = this.resortId,
    currentWeather = this.currentWeather.toDomain(),
    todayWeatherByTime = this.todayWeatherByTime.map { it.toDomain() },
    weeklyWeather = this.weeklyWeather.map { it.toDomain() }
)

fun SkiResortWeatherInfoResponse.CurrentWeather.toDomain() = SkiResortWeatherInfo.CurrentWeather(
    temperature = this.temperature,
    maxTemperature = this.maxTemperature,
    minTemperature = this.minTemperature,
    feelsLike = this.feelsLike,
    description = this.description,
    condition = WeatherCondition.findByKorean(this.condition)
)

fun SkiResortWeatherInfoResponse.HourlyWeather.toDomain() = SkiResortWeatherInfo.HourlyWeather(
    resortId = this.resortId,
    forecastTime = this.forecastTime,
    temperature = this.temperature,
    precipitationChance = this.precipitationChance,
    condition = this.condition,
    updatedAt = this.updatedAt
)

fun SkiResortWeatherInfoResponse.DailyWeather.toDomain() = SkiResortWeatherInfo.DailyWeather(
    day = this.day,
    date = this.date,
    precipitationChance = this.precipitationChance,
    maxTemperature = this.maxTemperature,
    minTemperature = this.minTemperature,
    condition = this.condition
)