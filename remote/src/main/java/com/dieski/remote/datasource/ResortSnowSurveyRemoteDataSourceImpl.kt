package com.dieski.remote.datasource

import com.dieski.analytics.AnalyticsLogger
import com.dieski.data.datasource.remote.ResortSnowSurveyRemoteDataSource
import com.dieski.data.model.TotalResortSnowSurveyDto
import com.dieski.domain.network.onFailure
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult
import com.dieski.domain.result.toResult
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
	): WResult<Boolean, DataError> =
		snowQualityService.submitSnowQualitySurvey(resortId, isPositive)
			.onFailure {
				logger.logError(throwable, "SnowQualityService - submitSnowQualitySurvey()에서 발생")
			}
			.toResult {
				true
			}


	override suspend fun fetchingSnowQualitySurveyResult(resortId: Long): WResult<TotalResortSnowSurveyDto, DataError> =
		snowQualityService.fetchSnowQualitySurveyResult(resortId)
			.onFailure {
				logger.logError(throwable, "SnowQualityService - fetchingSnowQualitySurveyResult()에서 발생")
			}
			.toResult {
				it.toData()
			}
}