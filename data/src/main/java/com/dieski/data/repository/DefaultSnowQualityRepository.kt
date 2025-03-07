package com.dieski.data.repository

import com.dieski.data.datasource.local.ResortSnowSurveyLocalDataSource
import com.dieski.data.datasource.remote.ResortSnowSurveyRemoteDataSource
import com.dieski.data.model.submitAvailable
import com.dieski.data.toDomainModel
import com.dieski.domain.dispatchers.Dispatcher
import com.dieski.domain.dispatchers.WeSkiDispatchers
import com.dieski.domain.extension.runSuspendCatching
import com.dieski.domain.model.TotalResortSnowQualitySurvey
import com.dieski.domain.model.exception.SnowSurveyAlreadyExistException
import com.dieski.domain.repository.SnowQualityRepository
import com.dieski.domain.util.DateUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
class DefaultSnowQualityRepository @Inject constructor(
	private val resortSnowSurveyRemoteDataSource: ResortSnowSurveyRemoteDataSource,
	private val resortSnowSurveyLocalDataSource: ResortSnowSurveyLocalDataSource,
	@Dispatcher(WeSkiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : SnowQualityRepository {

	override suspend fun submitSnowQualitySurvey(resortId: Long, isPositive: Boolean): Result<Unit> = runSuspendCatching {
		val findSurvey = resortSnowSurveyLocalDataSource.getSurveyByResortId(resortId)
		if (findSurvey?.submitAvailable() == false) throw SnowSurveyAlreadyExistException()

		resortSnowSurveyLocalDataSource.saveSurvey(resortId, DateUtil.createYYYYMMDDFormat())
		resortSnowSurveyRemoteDataSource.submitSnowQualitySurvey(resortId, isPositive)
	}

	override fun getTotalResortSnowQualitySurvey(resortId: Long): Flow<TotalResortSnowQualitySurvey> =
		flow {
			val totalResortSnowQualitySurvey = resortSnowSurveyRemoteDataSource
				.getTotalResortSnowQualitySurvey(resortId)
				.getOrThrow()
			emit(totalResortSnowQualitySurvey.toDomainModel())
		}
}