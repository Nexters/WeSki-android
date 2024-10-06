package com.dieski.data.repository

import com.dieski.data.datasource.WeSkiDataSource
import com.dieski.data.repository.mapper.toDomain
import com.dieski.domain.model.BriefResortInfo
import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.SnowMakingSurveyResult
import com.dieski.domain.model.TodayForecast
import com.dieski.domain.model.WeekWeatherInfo
import com.dieski.domain.repository.WeSkiRepository
import com.dieski.remote.dispatchers.Dispatcher
import com.dieski.remote.dispatchers.WeSkiDispatchers
import com.dieski.domain.network.NetworkResult
import com.dieski.domain.result.WError
import com.dieski.domain.result.WResult
import com.dieski.remote.model.request.SubmitSnowQualitySurveyRequest
import com.dieski.remote.service.SnowQualityService
import com.dieski.remote.service.WeSkiService
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

	override suspend fun fetchTodayForecast(): TodayForecast {
		return TodayForecast()
	}

	override suspend fun fetchWeekForecast(): List<WeekWeatherInfo> {
		return emptyList()
	}

	override suspend fun submitSnowQualitySurvey(
		resortId: Int,
		isLike: Boolean
	) {
		return withContext(ioDispatcher) {
			snowQualityService.submitSnowQualitySurvey(
				resortId,
				SubmitSnowQualitySurveyRequest(isLike)
			)
		}
	}

	override suspend fun fetchingSnowQualitySurveyResult(
		resortId: Int
	): SnowMakingSurveyResult? {
		return withContext(ioDispatcher) {
			val response = snowQualityService.fetchingSnowQualitySurveyResult(resortId)
			if (response is NetworkResult.Success) {
				response.data.toDomain()
			} else {
				null
			}
		}
	}
}