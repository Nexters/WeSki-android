package com.dieski.domain.repository

import com.dieski.domain.model.SnowQualitySurveyResult
import com.dieski.domain.result.WError
import com.dieski.domain.result.WResult

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
interface SnowQualityRepository {

	suspend fun submitSnowQualitySurvey(
		resortId: Long,
		isPositive: Boolean
	)

	suspend fun fetchSnowQualitySurveyResult(
		resortId: Long
	) : WResult<SnowQualitySurveyResult, WError>
}