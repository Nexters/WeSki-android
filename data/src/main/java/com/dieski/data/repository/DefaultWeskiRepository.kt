package com.dieski.data.repository

import com.dieski.data.repository.mapper.toDomain
import com.dieski.domain.model.BriefResortInfo
import com.dieski.domain.model.ResortWeatherInfo
import com.dieski.domain.model.SnowMakingSurveyResult
import com.dieski.domain.model.TodayForecast
import com.dieski.domain.model.WeekWeatherInfo
import com.dieski.domain.repository.WeSkiRepository
import com.dieski.remote.dispatchers.Dispatcher
import com.dieski.remote.dispatchers.WeSkiDispatchers
import com.dieski.domain.network.NetworkResult
import com.dieski.remote.model.request.SubmitSnowQualitySurveyRequest
import com.dieski.remote.service.SnowQualityService
import com.dieski.remote.service.WeSkiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
internal class DefaultWeskiRepository @Inject constructor(
	private val weSkiService: WeSkiService,
	private val snowQualityService: SnowQualityService,
	@Dispatcher(WeSkiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : WeSkiRepository {

	override suspend fun fetchResortWeatherInfoList(): List<ResortWeatherInfo> {
		return withContext(ioDispatcher) {
			weSkiService.fetchResortWeatherInfoList().map { it.toDomain() }
		}
	}

	override suspend fun fetchTodayForecast(): TodayForecast {
		return TodayForecast()
	}

	override suspend fun fetchWeekForecast(): List<WeekWeatherInfo> {
		return emptyList()
	}

	override suspend fun fetchBriefResortInfo(
		resortId: Int
	): BriefResortInfo {
		return withContext(ioDispatcher) {
			weSkiService.fetchBriefResortInfo(resortId).toDomain()
		}
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