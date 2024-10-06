package com.dieski.data.datasource

import com.dieski.analytics.AnalyticsLogger
import com.dieski.data.repository.mapper.toDomain
import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.network.onFailure
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult
import com.dieski.domain.result.toResult
import com.dieski.remote.service.WeSkiService
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/10/06
 */
class RemoteWeSkiDataSource @Inject constructor(
	private val weSkiService: WeSkiService,
	private val logger: AnalyticsLogger
) : WeSkiDataSource {

	override suspend fun fetchAllSkiResortsInfo(): WResult<List<SkiResortInfo>, DataError> {
		return weSkiService.fetchAllSkiResortsInfo()
			.onFailure {
				logger.logError(throwable, "WeSkiDataSource - fetchingSnowQualitySurveyResult()에서 발생")
			}
			.toResult { skiResortInfoList ->
				skiResortInfoList.map { it.toDomain() }
			}
	}

}