package com.dieski.data.repository

import android.text.format.DateUtils
import com.dieski.data.datasource.SnowMakerSurveyRecordDataSource
import com.dieski.data.datasource.SnowQualityDataSource
import com.dieski.data.datasource.di.Local
import com.dieski.data.datasource.di.Remote
import com.dieski.domain.model.SnowQualitySurveyResult
import com.dieski.domain.repository.SnowQualityRepository
import com.dieski.domain.result.WError
import com.dieski.domain.result.WResult
import com.dieski.domain.dispatchers.Dispatcher
import com.dieski.domain.dispatchers.WeSkiDispatchers
import com.dieski.domain.result.DataError
import com.dieski.domain.util.DateUtil
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
	@Remote private val remoteSnowQualityDataSource: SnowQualityDataSource,
	@Local private val localSnowQualityDataSource: SnowMakerSurveyRecordDataSource,
	@Dispatcher(WeSkiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : SnowQualityRepository {

	override suspend fun submitSnowQualitySurvey(resortId: Long, isPositive: Boolean):WResult<Boolean, WError> {
		return withContext(ioDispatcher) {
			when (val findRecordResult = localSnowQualityDataSource.findSnowMakerSurveyRecord(resortId)) {
				is WResult.Success -> {
					val data = findRecordResult.data
					if (data == null || data.checkSubmitDateIsToday().not()) {
						localSnowQualityDataSource.saveSnowMakerSurveyRecord(resortId, DateUtil.createYYYYMMDDFormat())
						remoteSnowQualityDataSource.submitSnowQualitySurvey(resortId, isPositive)
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
			remoteSnowQualityDataSource.fetchingSnowQualitySurveyResult(resortId)
		}
	}
}