package com.dieski.data.repository

import com.dieski.data.dispatchers.Dispatcher
import com.dieski.data.dispatchers.WeSkiDispatchers
import com.dieski.domain.model.ResortDailyWeatherInfo
import com.dieski.domain.model.ResortWeatherInfo
import com.dieski.domain.model.TodayForecast
import com.dieski.domain.repository.WeSkiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
internal class FakeWeskiRepository  @Inject constructor(
	@Dispatcher(WeSkiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : WeSkiRepository {

	override fun fetchResortWeatherInfoList() = flow {
		val resortDailyWeatherInfoList = listOf(
				ResortDailyWeatherInfo(day = "월요일",  weatherType ="normal", avgTemperature = 2,  minTemperature =  -7),
				ResortDailyWeatherInfo(day ="화요일", weatherType = "snow", avgTemperature = 0,  minTemperature = -3),
				ResortDailyWeatherInfo(day ="수요일", weatherType = "cloudy", avgTemperature = -5,  minTemperature = -2),
				ResortDailyWeatherInfo(day ="목요일", weatherType = "rain", avgTemperature = -5,  minTemperature = -10),
				ResortDailyWeatherInfo(day ="금요일", weatherType = "normal", avgTemperature = 5,  minTemperature = -1),
				ResortDailyWeatherInfo(day ="일요일", weatherType = "normal", avgTemperature = 6,  minTemperature = 3)
		)

		val resortWeatherInfoList = listOf(
			ResortWeatherInfo(
				name = "용평스키장 모나",
				operatingSlopeCount = 5,
				currentTemperature = 7,
				weatherType = "snow",
				weatherDescription = "흐리고 눈",
				weekWeatherInfo = resortDailyWeatherInfoList,
			),
			ResortWeatherInfo(
				name = "휘닉스 파크",
				operatingSlopeCount = 5,
				currentTemperature = -12,
				weatherType = "normal",
				weatherDescription = "맑음",
				weekWeatherInfo = resortDailyWeatherInfoList
			),
			ResortWeatherInfo(
				name = "곤지암 리조트",
				operatingSlopeCount = 5,
				currentTemperature = 4,
				weatherType = "cloudy",
				weatherDescription = "흐림",
				weekWeatherInfo = resortDailyWeatherInfoList
			),
			ResortWeatherInfo(
				name = "비발디 파크",
				operatingSlopeCount = 5,
				currentTemperature = 1,
				weatherType = "rain",
				weatherDescription = "폭우",
				weekWeatherInfo = resortDailyWeatherInfoList
			),
			ResortWeatherInfo(
				name = "용평스키장 모나",
				operatingSlopeCount = 5,
				currentTemperature = -3,
				weatherType = "snow",
				weatherDescription = "흐리고 눈",
				weekWeatherInfo = resortDailyWeatherInfoList,
			),
			ResortWeatherInfo(
				name = "휘닉스 파크",
				operatingSlopeCount = 5,
				currentTemperature = 10,
				weatherType = "normal",
				weatherDescription = "맑음",
				weekWeatherInfo = resortDailyWeatherInfoList
			),
			ResortWeatherInfo(
				name = "곤지암 리조트",
				operatingSlopeCount = 5,
				currentTemperature = 3,
				weatherType = "cloudy",
				weatherDescription = "흐림",
				weekWeatherInfo = resortDailyWeatherInfoList
			),
			ResortWeatherInfo(
				name = "비발디 파크",
				operatingSlopeCount = 5,
				currentTemperature = 2,
				weatherType = "rain",
				weatherDescription = "폭우",
				weekWeatherInfo = resortDailyWeatherInfoList
			)
		)

		emit(resortWeatherInfoList)
	}.flowOn(ioDispatcher)

	override fun fetchTodayForecast() = flow {
		val todayForecast = TodayForecast(
			currentTemperature = 7,
			perceivedTemperature = 5,
			weatherDescription = "흐리고 눈",
			highestTemperature = 10,
			lowestTemperature = 3,
			hourlyForecastWeatherInfoList = listOf(
				TodayForecast.HourlyForecastWeatherInfo(
					time = "오전 8시",
					weatherType = "snow",
					temperature = -5,
					chanceOfRain = 20
				),
				TodayForecast.HourlyForecastWeatherInfo(
					time = "오전 10시",
					weatherType = "snow",
					temperature = -4,
					chanceOfRain = 30
				),
				TodayForecast.HourlyForecastWeatherInfo(
					time = "오후 12시",
					weatherType = "snow",
					temperature = -3,
					chanceOfRain = 30
				),
				TodayForecast.HourlyForecastWeatherInfo(
					time = "오후 2시",
					weatherType = "snow",
					temperature = -2,
					chanceOfRain = 30
				),
				TodayForecast.HourlyForecastWeatherInfo(
					time = "오후 4시",
					weatherType = "snow",
					temperature = -1,
					chanceOfRain = 30
				),
				TodayForecast.HourlyForecastWeatherInfo(
					time = "오후 6시",
					weatherType = "snow",
					temperature = 0,
					chanceOfRain = 30
				),
				TodayForecast.HourlyForecastWeatherInfo(
					time = "오후 8시",
					weatherType = "snow",
					temperature = 1,
					chanceOfRain = 30
				),
				TodayForecast.HourlyForecastWeatherInfo(
					time = "오후 10시",
					weatherType = "snow",
					temperature = -2,
					chanceOfRain = 30
				)
			)
		)

		emit(todayForecast)
	}.flowOn(ioDispatcher)
}