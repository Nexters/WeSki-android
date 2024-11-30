package com.dieski.data.datasource

import com.dieski.domain.model.ResortSnowMakerSurveyRecord
import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.result.DataError
import com.dieski.domain.result.WResult

/**
 *
 * @author   JGeun
 * @created  2024/11/29
 */
interface ResortBookmarkDataSource {

	suspend fun fetchResortBookmark(): WResult<List<SkiResortInfo>, DataError>

	suspend fun saveResortBookmark(
		resortId: Long
	): WResult<Boolean, DataError>

	suspend fun findSnowMakerSurveyRecord(
		resortId: Long
	): WResult<ResortSnowMakerSurveyRecord?, DataError>
}