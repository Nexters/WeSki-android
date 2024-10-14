package com.dieski.data.repository

import com.dieski.data.datasource.SnowQualityDataSource
import com.dieski.domain.model.SnowQualitySurveyResult
import com.dieski.domain.repository.SnowQualityRepository
import com.dieski.domain.result.WError
import com.dieski.domain.result.WResult
import com.dieski.domain.dispatchers.Dispatcher
import com.dieski.domain.dispatchers.WeSkiDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
class DefaultSnowQualityRepository @Inject constructor(
	@Named("remote") private val remoteSnowQualityDataSource: SnowQualityDataSource,
	@Dispatcher(WeSkiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : SnowQualityRepository {

	override suspend fun submitSnowQualitySurvey(resortId: Long, isPositive: Boolean) {
		return withContext(ioDispatcher) {
			remoteSnowQualityDataSource.submitSnowQualitySurvey(resortId, isPositive)
		}
	}

	override suspend fun fetchSnowQualitySurveyResult(resortId: Long): WResult<SnowQualitySurveyResult, WError> {
		return withContext(ioDispatcher) {
			remoteSnowQualityDataSource.fetchingSnowQualitySurveyResult(resortId)
		}
	}
}