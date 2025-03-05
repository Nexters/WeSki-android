package com.dieski.remote.datasource

import com.dieski.analytics.AnalyticsLogger
import com.dieski.data.datasource.remote.ResortRemoteDataSource
import com.dieski.data.model.ResortInfoDto
import com.dieski.data.model.ResortWeatherInfoDto
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
class ResortRemoteDataSourceImpl @Inject constructor(
	private val weSkiService: WeSkiService,
	private val logger: AnalyticsLogger
) : ResortRemoteDataSource {

	override suspend fun fetchAllSkiResortsInfo(): WResult<List<ResortInfoDto>, DataError> {
		return weSkiService.fetchAllSkiResortsInfo()
			.onFailure {
				logger.logError(throwable, "WeSkiDataSource - fetchingSnowQualitySurveyResult()에서 발생")
			}
			.toResult { skiResortInfoList ->
				skiResortInfoList.map { it.toData() }
			}
	}

	override suspend fun fetchSkiResortWeatherInfo(resortId: Long): WResult<ResortWeatherInfoDto, DataError> {
		return weSkiService.fetchSkiResortWeatherInfo(resortId)
			.onFailure {
				logger.logError(throwable, "WeSkiDataSource - fetchSkiResortWeatherInfo()에서 발생")
			}
			.toResult { skiResortWeatherInfoResponse ->
				skiResortWeatherInfoResponse.toData()
			}
	}
}