package com.dieski.data.datasource.remote

import com.dieski.data.model.TotalResortSnowSurveyDto

/**
 *
 * @author   JGeun
 * @created  2025/03/05
 */
interface ResortSnowSurveyRemoteDataSource {

	suspend fun submitSnowQualitySurvey(
		resortId: Long,
		isPositive: Boolean
	): Result<Unit>

	suspend fun getTotalResortSnowQualitySurvey(resortId: Long): Result<TotalResortSnowSurveyDto>
}