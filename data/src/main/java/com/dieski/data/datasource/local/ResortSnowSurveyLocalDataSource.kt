package com.dieski.data.datasource.local

import com.dieski.data.model.MyResortSnowSurveyDto

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
interface ResortSnowSurveyLocalDataSource {

	suspend fun saveSurvey(
		resortId: Long,
		submitDate: String
	): Result<Unit>

	suspend fun getSurveyByResortId(
		resortId: Long
	): MyResortSnowSurveyDto?

	suspend fun deleteSurveysByResortIds(
		resortIdList: List<Long>
	): Result<Unit>
}