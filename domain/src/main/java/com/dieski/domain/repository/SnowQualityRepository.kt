package com.dieski.domain.repository

import com.dieski.domain.model.SnowMakingSurveyResult
import com.dieski.domain.result.WError
import com.dieski.domain.result.WResult

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
interface SnowQualityRepository {

	suspend fun submitSnowQualitySurvey(resortId: Int, isLike: Boolean) : Unit

	suspend fun fetchingSnowQualitySurveyResult(resortId: Long) : WResult<SnowMakingSurveyResult, WError>
}