package com.dieski.domain.repository

import com.dieski.domain.model.TotalResortSnowQualitySurvey
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
interface SnowQualityRepository {

	suspend fun submitSnowQualitySurvey(
		resortId: Long,
		isPositive: Boolean
	): Result<Unit>

	fun getTotalResortSnowQualitySurvey(
		resortId: Long
	) : Flow<TotalResortSnowQualitySurvey>
}