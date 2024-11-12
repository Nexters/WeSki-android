package com.dieski.data.datasource

import com.dieski.domain.model.ResortSnowMakerSurveyRecord
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
interface SnowMakerSurveyRecordDataSource {

	suspend fun saveSnowMakerSurveyRecord(
		resortId: Long,
		submitDate: String
	): WResult<Boolean, DataError>

	suspend fun findSnowMakerSurveyRecord(
		resortId: Long
	): WResult<ResortSnowMakerSurveyRecord?, DataError>
}