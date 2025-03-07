package com.dieski.local.datasource

import com.dieski.data.datasource.local.ResortSnowSurveyLocalDataSource
import com.dieski.data.model.MyResortSnowSurveyDto
import com.dieski.domain.dispatchers.Dispatcher
import com.dieski.domain.dispatchers.WeSkiDispatchers
import com.dieski.domain.extension.runSuspendCatching
import com.dieski.local.entity.ResortSnowSurveyEntity
import com.dieski.local.room.dao.ResortSnowSurveyDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
class ResortSnowSurveyLocalDataSourceIImpl @Inject constructor(
	private val resortSnowSurveyDao: ResortSnowSurveyDao,
    @Dispatcher(WeSkiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : ResortSnowSurveyLocalDataSource {

	override suspend fun saveSurvey(resortId: Long, submitDate: String): Result<Unit> = runSuspendCatching {
		resortSnowSurveyDao.saveSurvey(
			ResortSnowSurveyEntity(
				resortId = resortId,
				submitDate = submitDate
			)
		)
	}

	override suspend fun getSurveyByResortId(resortId: Long): MyResortSnowSurveyDto? {
		return withContext(ioDispatcher) {
            resortSnowSurveyDao.getSurveyByResortId(resortId)?.toData()
        }
	}

	override suspend fun deleteSurveysByResortIds(resortIdList: List<Long>): Result<Unit> = runSuspendCatching {
        resortSnowSurveyDao.deleteSurveysByResortIds(resortIdList)
	}
}