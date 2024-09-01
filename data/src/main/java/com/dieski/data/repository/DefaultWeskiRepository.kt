package com.dieski.data.repository

import android.net.Network
import com.dieski.data.dispatchers.Dispatcher
import com.dieski.data.dispatchers.WeSkiDispatchers
import com.dieski.data.remote.network.model.request.SubmitSnowQualitySurveyRequest
import com.dieski.data.remote.network.service.WeSkiService
import com.dieski.data.repository.mapper.toDomain
import com.dieski.domain.model.BriefResortInfo
import com.dieski.domain.model.NetworkResult
import com.dieski.domain.model.ResortWeatherInfo
import com.dieski.domain.model.SnowMakingSurveyResult
import com.dieski.domain.model.TodayForecast
import com.dieski.domain.model.WeekWeatherInfo
import com.dieski.domain.model.onFailure
import com.dieski.domain.model.onSuccess
import com.dieski.domain.model.transform
import com.dieski.domain.repository.WeSkiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
internal class DefaultWeskiRepository @Inject constructor(
	private val weSkiService: WeSkiService,
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
			weSkiService.submitSnowQualitySurvey(
				resortId,
				SubmitSnowQualitySurveyRequest(isLike)
			)
		}
	}

	override suspend fun fetchingSnowQualitySurveyResult(
		resortId: Int
	): SnowMakingSurveyResult? {
		return withContext(ioDispatcher) {
			val response = weSkiService.fetchingSnowQualitySurveyResult(resortId)
			if (response is NetworkResult.Success) {
				response.data.toDomain()
			} else {
				null
			}
		}
	}
}