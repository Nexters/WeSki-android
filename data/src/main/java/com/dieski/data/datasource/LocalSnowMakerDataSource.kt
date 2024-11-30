package com.dieski.data.datasource

import com.dieski.data.repository.mapper.toDomain
import com.dieski.domain.model.ResortSnowMakerSurveyRecord
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult
import com.dieski.local.dao.ResortSnowMakerSurveyRecordDao
import com.dieski.local.entity.ResortSnowMakerSurveyRecordEntity
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
class LocalSnowMakerDataSource @Inject constructor(
	private val snowMakerSurveyRecordDao: ResortSnowMakerSurveyRecordDao
) : SnowMakerSurveyRecordDataSource {

	override suspend fun saveSnowMakerSurveyRecord(resortId: Long, submitDate: String): WResult<Boolean, DataError> {
		return try {
			snowMakerSurveyRecordDao.saveResortSnowMakerSurveyRecord(
				ResortSnowMakerSurveyRecordEntity(
					resortId = resortId,
					submitDate = submitDate
				)
			)
			WResult.Success(true)
		} catch (e: Exception) {
			WResult.Error(DataError.Local.UnknownError)
		}
	}

	override suspend fun findSnowMakerSurveyRecord(resortId: Long): WResult<ResortSnowMakerSurveyRecord?, DataError> {
		return try {
			val snowMakerSurveyRecordDao = snowMakerSurveyRecordDao.findSnowMakerSurveyRecord(resortId)
			WResult.Success(snowMakerSurveyRecordDao?.toDomain())
		} catch (e: Exception) {
			WResult.Error(DataError.Local.UnknownError)
		}
	}

	override suspend fun deleteSnowMakerSurveyRecordList(resortIdList: List<Long>): WResult<Boolean, DataError> {
		return try {
			snowMakerSurveyRecordDao.deleteResortSnowMakerSurveyRecordList(resortIdList)
			WResult.Success(true)
		} catch (e: Exception) {
			WResult.Error(DataError.Local.UnknownError)
		}
	}
}