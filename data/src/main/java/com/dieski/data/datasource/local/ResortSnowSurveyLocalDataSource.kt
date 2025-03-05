package com.dieski.data.datasource.local

import com.dieski.data.model.MyResortSnowSurveyDto
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
	): WResult<MyResortSnowSurveyDto?, DataError>

	suspend fun deleteSurveysByResortIds(
		resortIdList: List<Long>
	): WResult<Boolean, DataError>
}