package com.dieski.domain.repository

import com.dieski.domain.model.BriefResortInfo
import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.SnowMakingSurveyResult
import com.dieski.domain.model.TodayForecast
import com.dieski.domain.model.WeekWeatherInfo
import com.dieski.domain.result.WError
import com.dieski.domain.result.WResult

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
interface WeSkiRepository {

	suspend fun fetchAllSkiResortsInfo() : WResult<List<SkiResortInfo>, WError>

	suspend fun fetchTodayForecast(): TodayForecast

	suspend fun fetchWeekForecast(): List<WeekWeatherInfo>

	suspend fun submitSnowQualitySurvey(resortId: Int, isLike: Boolean)

	suspend fun fetchingSnowQualitySurveyResult(resortId: Int) : SnowMakingSurveyResult?
}