package com.dieski.remote.datasource

import com.dieski.analytics.AnalyticsLogger
import com.dieski.data.datasource.remote.ResortRemoteDataSource
import com.dieski.data.model.ResortInfoDto
import com.dieski.data.model.ResortWeatherInfoDto
import com.dieski.domain.dispatchers.Dispatcher
import com.dieski.domain.dispatchers.WeSkiDispatchers
import com.dieski.remote.model.network.NetworkResult
import com.dieski.remote.model.network.getOrThrow
import com.dieski.remote.model.network.onFailure
import com.dieski.remote.service.WeSkiService
import com.dieski.remote.toData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/10/06
 */
class ResortRemoteDataSourceImpl @Inject constructor(
	private val weSkiService: WeSkiService,
	private val logger: AnalyticsLogger,
	@Dispatcher(WeSkiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : ResortRemoteDataSource {

	override fun getSkiResortList(): Flow<List<ResortInfoDto>> = flow {
		val skiResortList = weSkiService.getSkiResortList()
			.onFailure {
				logger.logError(throwable, "WeSkiDataSource - getSkiResortList()에서 발생")
			}
			.getOrThrow()
			.toData()
		emit (skiResortList)
	}

	override fun getSkiResortWeatherInfo(resortId: Long): Flow<ResortWeatherInfoDto> = flow {
		when (val response = weSkiService.getSkiResortWeatherInfo(resortId)) {
			is NetworkResult.Success -> emit(response.data.toData())
			is NetworkResult.Failure -> {
				logger.logError(response.throwable, "WeSkiDataSource - fetchSkiResortWeatherInfo()에서 발생")
			}
		}
	}.flowOn(ioDispatcher)
}