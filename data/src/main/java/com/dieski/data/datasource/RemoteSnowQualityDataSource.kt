package com.dieski.data.datasource

import com.dieski.analytics.AnalyticsLogger
import com.dieski.data.repository.mapper.toDomain
import com.dieski.domain.exception.getOrThrow
import com.dieski.domain.exception.onFailure
import com.dieski.remote.model.request.SubmitSnowQualitySurveyRequest
import com.dieski.remote.service.SnowQualityService
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
class RemoteSnowQualityDataSource @Inject constructor(
	private val snowQualityService: SnowQualityService,
	private val logger: AnalyticsLogger
) : SnowQualityDataSource {

	override suspend fun submitSnowQualitySurvey(resortId: Int, isLike: Boolean) {
		snowQualityService.submitSnowQualitySurvey(resortId, SubmitSnowQualitySurveyRequest(isLike))
			.onFailure {
				logger.logError(throwable, "SnowQualityService - submitSnowQualitySurvey()에서 발생")
			}
	}

	override suspend fun fetchingSnowQualitySurveyResult(resortId: Int) =
		snowQualityService.fetchingSnowQualitySurveyResult(resortId)
			.onFailure {
				logger.logError(throwable, "SnowQualityService - fetchingSnowQualitySurveyResult()에서 발생")
			}
			.getOrThrow()
			.toDomain()
}