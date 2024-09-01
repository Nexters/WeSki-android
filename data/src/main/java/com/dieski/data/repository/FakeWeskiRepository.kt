package com.dieski.data.repository

import android.util.Log
import com.dieski.data.dispatchers.Dispatcher
import com.dieski.data.dispatchers.WeSkiDispatchers
import com.dieski.data.remote.network.model.response.ResortWeatherInfoResponse
import com.dieski.domain.model.BriefResortInfo
import com.dieski.domain.model.NetworkResult
import com.dieski.domain.model.ResortApiData
import com.dieski.domain.model.ResortWeatherInfo
import com.dieski.domain.model.SnowMakingSurveyResult
import com.dieski.domain.model.TodayForecast
import com.dieski.domain.model.WeekWeatherInfo
import com.dieski.domain.repository.WeSkiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
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


			val resortWeatherInfoList = ResortApiData.entries.map {
				ResortWeatherInfo(
					resortId = it.key,
					resortName = it.resortName,
					resortWebKey = it.webKey,
					operatingSlopeCount = 5,
					currentTemperature = it.temperature,
					weatherType = it.weatherType,
					weatherDescription = it.weatherDescription,
					resortDailyWeatherInfoList = it.resortDailyWeatherInfo,
				)
			}

			resortWeatherInfoList
		}
	}

	override suspend fun fetchTodayForecast(): TodayForecast {
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

		return todayForecast
	}

	override suspend fun fetchWeekForecast(): List<WeekWeatherInfo> {
		return listOf(
			WeekWeatherInfo(
				day = "토요일",
				date = "8.24",
				weatherType = "snow",
				chanceOfRain = 50,
				highestTemperature = 10,
				lowestTemperature = 3
			),
			WeekWeatherInfo(
				day = "화요일",
				date = "8.25",
				chanceOfRain = 30,
				weatherType = "snow",
				highestTemperature = -10,
				lowestTemperature = 3
			),
			WeekWeatherInfo(
				day = "수요일",
				date = "8.26",
				chanceOfRain = 10,
				weatherType = "snow",
				highestTemperature = 10,
				lowestTemperature = 3
			),
			WeekWeatherInfo(
				day = "목요일",
				date = "8.27",
				chanceOfRain = 5,
				weatherType = "snow",
				highestTemperature = 10,
				lowestTemperature = 3
			),
			WeekWeatherInfo(
				day = "금요일",
				date = "8.28",
				chanceOfRain = 33,
				weatherType = "snow",
				highestTemperature = 10,
				lowestTemperature = 3
			),
			WeekWeatherInfo(
				day = "토요일",
				date = "8.29",
				chanceOfRain = 17,
				weatherType = "snow",
				highestTemperature = 10,
				lowestTemperature = 3
			),
			WeekWeatherInfo(
				day = "일요일",
				date = "2024-08-18",
				chanceOfRain = 80,
				weatherType = "snow",
				highestTemperature = 10,
				lowestTemperature = 3
			)
		)
	}

	override suspend fun fetchBriefResortInfo(resortId: Int): BriefResortInfo {
		TODO("Not yet implemented")
	}

	override suspend fun submitSnowQualitySurvey(resortId: Int, isLike: Boolean) {
		TODO("Not yet implemented")
	}

	override suspend fun fetchingSnowQualitySurveyResult(resortId: Int): SnowMakingSurveyResult? {
		TODO("Not yet implemented")
	}
}