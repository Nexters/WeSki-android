package com.dieski.data.repository

import com.dieski.data.datasource.WeSkiDataSource
import com.dieski.data.repository.mapper.toDomain
import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.SkiResortWeatherInfo
import com.dieski.domain.model.SnowQualitySurveyResult
import com.dieski.domain.repository.WeSkiRepository
import com.dieski.domain.dispatchers.Dispatcher
import com.dieski.domain.dispatchers.WeSkiDispatchers
import com.dieski.domain.network.NetworkResult
import com.dieski.domain.result.WError
import com.dieski.domain.result.WResult
import com.dieski.remote.model.request.SubmitSnowQualitySurveyRequest
import com.dieski.remote.service.SnowQualityService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
internal class DefaultWeskiRepository @Inject constructor(
	@Named("remote") private val remoteWeSkiDataSource: WeSkiDataSource,
	private val snowQualityService: SnowQualityService,
	@Dispatcher(WeSkiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : WeSkiRepository {

	override suspend fun fetchAllSkiResortsInfo(): WResult<List<SkiResortInfo>, WError> {
		return withContext(ioDispatcher) {
			remoteWeSkiDataSource.fetchAllSkiResortsInfo()
		}
	}

	override suspend fun fetchSkiResortWeatherInfo(resortId: Long): WResult<SkiResortWeatherInfo, WError> {
		return withContext(ioDispatcher) {
			remoteWeSkiDataSource.fetchSkiResortWeatherInfo(resortId)
		}
	}
}