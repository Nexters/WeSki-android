package com.dieski.data.repository

import com.dieski.data.datasource.local.ResortSnowSurveyLocalDataSource
import com.dieski.data.datasource.remote.ResortSnowSurveyRemoteDataSource
import com.dieski.data.toDomainModel
import com.dieski.domain.dispatchers.Dispatcher
import com.dieski.domain.dispatchers.WeSkiDispatchers
import com.dieski.domain.model.SnowQualitySurveyResult
import com.dieski.domain.repository.SnowQualityRepository
import com.dieski.domain.result.WError
import com.dieski.domain.result.WResult
import com.dieski.domain.util.DateUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
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

	override suspend fun submitSnowQualitySurvey(resortId: Long, isPositive: Boolean): WResult<Boolean, WError> {
		return withContext(ioDispatcher) {
			when (val findRecordResult = resortSnowSurveyLocalDataSource.getSurveyByResortId(resortId)) {
				is WResult.Success -> {
					val data = findRecordResult.data?.toDomain()
					if (data == null || data.checkSubmitDateIsToday().not()) {
						resortSnowSurveyLocalDataSource.saveSurvey(resortId, DateUtil.createYYYYMMDDFormat())
						resortSnowSurveyRemoteDataSource.submitSnowQualitySurvey(resortId, isPositive)
						WResult.Success(true)
					} else {
						WResult.Success(false)
					}
				}

				is WResult.Error -> {
					WResult.Error(findRecordResult.error)
				}
			}
		}
	}

	override suspend fun fetchSnowQualitySurveyResult(resortId: Long): WResult<SnowQualitySurveyResult, WError> {
		return withContext(ioDispatcher) {
			when (val result = resortSnowSurveyRemoteDataSource.fetchingSnowQualitySurveyResult(resortId)) {
				is WResult.Success -> {
					WResult.Success(result.data.toDomain())
				}

				is WResult.Error -> {
					result.toDomainModel()
				}
			}
		}
	}
}