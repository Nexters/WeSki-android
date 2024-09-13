package com.dieski.domain.repository

import com.dieski.domain.model.BriefResortInfo
import com.dieski.domain.model.ResortWeatherInfo
import com.dieski.domain.model.SnowMakingSurveyResult
import com.dieski.domain.model.TodayForecast
import com.dieski.domain.model.WeekWeatherInfo

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
interface WeSkiRepository {

	suspend fun fetchResortWeatherInfoList() : List<ResortWeatherInfo>

	suspend fun fetchTodayForecast(): TodayForecast

	suspend fun fetchWeekForecast(): List<WeekWeatherInfo>

	suspend fun fetchBriefResortInfo(
		resortId: Int
	): BriefResortInfo

	suspend fun submitSnowQualitySurvey(resortId: Int, isLike: Boolean)

	suspend fun fetchingSnowQualitySurveyResult(resortId: Int) : SnowMakingSurveyResult?
}