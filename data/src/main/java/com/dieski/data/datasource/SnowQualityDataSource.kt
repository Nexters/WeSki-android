package com.dieski.data.datasource

import com.dieski.domain.model.SnowQualitySurveyResult
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
interface SnowQualityDataSource {

	suspend fun submitSnowQualitySurvey(
		resortId: Long,
		isPositive: Boolean
	): WResult<Boolean, DataError>

	suspend fun fetchingSnowQualitySurveyResult(
		resortId: Long
	) : WResult<SnowQualitySurveyResult, DataError>
}