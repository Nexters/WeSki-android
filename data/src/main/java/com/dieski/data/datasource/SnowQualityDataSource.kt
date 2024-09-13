package com.dieski.data.datasource

import com.dieski.domain.model.SnowMakingSurveyResult

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
interface SnowQualityDataSource {

	suspend fun submitSnowQualitySurvey(resortId: Int, isLike: Boolean) : Unit

	suspend fun fetchingSnowQualitySurveyResult(resortId: Int) : SnowMakingSurveyResult
}