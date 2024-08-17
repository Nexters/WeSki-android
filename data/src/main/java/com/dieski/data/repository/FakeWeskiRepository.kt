package com.dieski.data.repository

import com.dieski.data.dispatchers.Dispatcher
import com.dieski.data.dispatchers.WeSkiDispatchers
import com.dieski.data.remote.network.model.response.ResortWeatherInfoResponse
import com.dieski.domain.model.ResortWeatherInfo
import com.dieski.domain.model.TodayForecast
import com.dieski.domain.repository.WeSkiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
internal class FakeWeskiRepository  @Inject constructor(
	@Dispatcher(WeSkiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : WeSkiRepository {

	override suspend fun fetchResortWeatherInfoList(): List<ResortWeatherInfo> {
		return withContext(ioDispatcher) {
			val resortDailyWeatherInfoList = listOf(
				ResortWeatherInfo.ResortDailyWeatherInfo(day = "월요일", weatherType = "normal", maxTemperature = 2, minTemperature = -7),
				ResortWeatherInfo.ResortDailyWeatherInfo(day = "화요일", weatherType = "snow", maxTemperature = 0, minTemperature = -3),
				ResortWeatherInfo.ResortDailyWeatherInfo(day = "수요일", weatherType = "cloudy", maxTemperature = -5, minTemperature = -2),
				ResortWeatherInfo.ResortDailyWeatherInfo(day = "목요일", weatherType = "rain", maxTemperature = -5, minTemperature = -10),
				ResortWeatherInfo.ResortDailyWeatherInfo(day = "금요일", weatherType = "normal", maxTemperature = 5, minTemperature = -1),
				ResortWeatherInfo.ResortDailyWeatherInfo(day = "일요일", weatherType = "normal", maxTemperature = 6, minTemperature = 3)
			)

			val resortWeatherInfoList = listOf(
				ResortWeatherInfo(
					resortName = "용평스키장 모나",
					operatingSlopeCount = 5,
					currentTemperature = 7,
					weatherType = "snow",
					weatherDescription = "흐리고 눈",
					resortDailyWeatherInfoList = resortDailyWeatherInfoList,
				),
				ResortWeatherInfo(
					resortName = "휘닉스 파크",
					operatingSlopeCount = 5,
					currentTemperature = -12,
					weatherType = "normal",
					weatherDescription = "맑음",
					resortDailyWeatherInfoList = resortDailyWeatherInfoList
				),
				ResortWeatherInfo(
					resortName = "곤지암 리조트",
					operatingSlopeCount = 5,
					currentTemperature = 4,
					weatherType = "cloudy",
					weatherDescription = "흐림",
					resortDailyWeatherInfoList = resortDailyWeatherInfoList
				),
				ResortWeatherInfo(
					resortName = "비발디 파크",
					operatingSlopeCount = 5,
					currentTemperature = 1,
					weatherType = "rain",
					weatherDescription = "폭우",
					resortDailyWeatherInfoList = resortDailyWeatherInfoList
				),
				ResortWeatherInfo(
					resortName = "용평스키장 모나",
					operatingSlopeCount = 5,
					currentTemperature = -3,
					weatherType = "snow",
					weatherDescription = "흐리고 눈",
					resortDailyWeatherInfoList = resortDailyWeatherInfoList,
				),
				ResortWeatherInfo(
					resortName = "휘닉스 파크",
					operatingSlopeCount = 5,
					currentTemperature = 10,
					weatherType = "normal",
					weatherDescription = "맑음",
					resortDailyWeatherInfoList = resortDailyWeatherInfoList
				),
				ResortWeatherInfo(
					resortName = "곤지암 리조트",
					operatingSlopeCount = 5,
					currentTemperature = 3,
					weatherType = "cloudy",
					weatherDescription = "흐림",
					resortDailyWeatherInfoList = resortDailyWeatherInfoList
				),
				ResortWeatherInfo(
					resortName = "비발디 파크",
					operatingSlopeCount = 5,
					currentTemperature = 2,
					weatherType = "rain",
					weatherDescription = "폭우",
					resortDailyWeatherInfoList = resortDailyWeatherInfoList
				)
			)

			resortWeatherInfoList
		}
	}

	override suspend fun fetchTodayForecast() = flow {
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