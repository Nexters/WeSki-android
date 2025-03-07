package com.dieski.remote.datasource

import com.dieski.analytics.AnalyticsLogger
import com.dieski.data.datasource.remote.ResortSnowSurveyRemoteDataSource
import com.dieski.data.model.TotalResortSnowSurveyDto
import com.dieski.domain.extension.runSuspendCatching
import com.dieski.remote.model.network.getOrThrow
import com.dieski.remote.model.network.onFailure
import com.dieski.remote.service.SnowQualityService
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
class ResortSnowSurveyRemoteDataSourceImpl @Inject constructor(
	private val snowQualityService: SnowQualityService,
	private val logger: AnalyticsLogger
) : ResortSnowSurveyRemoteDataSource {

	override suspend fun submitSnowQualitySurvey(
		resortId: Long,
		isPositive: Boolean
	): Result<Unit> = runSuspendCatching {
		snowQualityService.submitSnowQualitySurvey(resortId, isPositive)
			.onFailure {
				logger.logError(throwable, "SnowQualityService - submitSnowQualitySurvey()에서 발생")
			}
			.getOrThrow()
	}

	override suspend fun getTotalResortSnowQualitySurvey(resortId: Long): Result<TotalResortSnowSurveyDto> = runSuspendCatching {
		snowQualityService.getTotalResortSnowQualitySurvey(resortId)
			.onFailure {
				logger.logError(
					throwable,
					"SnowQualityService - getTotalResortSnowQualitySurvey()에서 발생"
				)
			}
			.getOrThrow()
			.toData()
	}
}