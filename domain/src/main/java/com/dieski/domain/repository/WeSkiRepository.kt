package com.dieski.domain.repository

import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.SkiResortWeatherInfo
import com.dieski.domain.model.SnowQualitySurveyResult
import com.dieski.domain.result.WError
import com.dieski.domain.result.WResult

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
interface WeSkiRepository {

	suspend fun fetchAllSkiResortsInfo() : WResult<List<SkiResortInfo>, WError>

	suspend fun fetchSkiResortWeatherInfo(resortId: Long) : WResult<SkiResortWeatherInfo, WError>

	suspend fun saveResortBookmark(resortId: Long)

	suspend fun deleteResortBookmark(resortId: Long)
}