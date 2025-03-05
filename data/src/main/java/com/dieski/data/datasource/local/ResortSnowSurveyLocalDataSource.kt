package com.dieski.data.datasource.local

import com.dieski.data.model.ResortSnowSurveyDto
import com.dieski.domain.model.ResortSnowMakerSurveyRecord
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
interface ResortSnowSurveyLocalDataSource {

	suspend fun saveSurvey(
		resortId: Long,
		submitDate: String
	): WResult<Boolean, DataError>

	suspend fun getSurveyByResortId(
		resortId: Long
	): WResult<ResortSnowSurveyDto?, DataError>

	suspend fun deleteSurveysByResortIds(
		resortIdList: List<Long>
	): WResult<Boolean, DataError>
}