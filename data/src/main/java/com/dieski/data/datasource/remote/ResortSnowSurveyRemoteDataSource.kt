package com.dieski.data.datasource.remote

import com.dieski.data.model.TotalResortSnowSurveyDto
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult

/**
 *
 * @author   JGeun
 * @created  2025/03/05
 */
interface ResortSnowSurveyRemoteDataSource {

	suspend fun submitSnowQualitySurvey(
		resortId: Long,
		isPositive: Boolean
	): WResult<Boolean, DataError>

	suspend fun fetchingSnowQualitySurveyResult(
		resortId: Long
	): WResult<TotalResortSnowSurveyDto, DataError>
}