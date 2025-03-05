package com.dieski.local.datasource

import com.dieski.data.datasource.local.ResortSnowSurveyLocalDataSource
import com.dieski.data.model.ResortSnowSurveyDto
import com.dieski.data.repository.mapper.toDomain
import com.dieski.domain.model.ResortSnowMakerSurveyRecord
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult
import com.dieski.local.entity.ResortSnowSurveyEntity
import com.dieski.local.room.dao.ResortSnowSurveyDao
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
class ResortSnowSurveyLocalDataSourceIImpl @Inject constructor(
	private val resortSnowSurveyDao: ResortSnowSurveyDao
) : ResortSnowSurveyLocalDataSource {

	override suspend fun saveSurvey(resortId: Long, submitDate: String): WResult<Boolean, DataError> {
		return try {
			resortSnowSurveyDao.saveSurvey(
				ResortSnowSurveyEntity(
					resortId = resortId,
					submitDate = submitDate
				)
			)
			WResult.Success(true)
		} catch (e: Exception) {
			WResult.Error(DataError.Local.UnknownError)
		}
	}

	override suspend fun getSurveyByResortId(resortId: Long): WResult<ResortSnowSurveyDto?, DataError> {
		return try {
			val snowMakerSurveyRecordDao = resortSnowSurveyDao.getSurveyByResortId(resortId)
			WResult.Success(snowMakerSurveyRecordDao?.toData())
		} catch (e: Exception) {
			WResult.Error(DataError.Local.UnknownError)
		}
	}

	override suspend fun deleteSurveysByResortIds(resortIdList: List<Long>): WResult<Boolean, DataError> {
		return try {
			resortSnowSurveyDao.deleteSurveysByResortIds(resortIdList)
			WResult.Success(true)
		} catch (e: Exception) {
			WResult.Error(DataError.Local.UnknownError)
		}
	}
}