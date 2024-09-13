package com.dieski.data.repository.mapper

import com.dieski.domain.model.SnowMakingSurveyResult
import com.dieski.remote.model.response.SnowQualitySurveyResultResponse

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
fun SnowQualitySurveyResultResponse.toDomain() = SnowMakingSurveyResult(
	totalNum = totalNum,
	likeNum = likeNum
)