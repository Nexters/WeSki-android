package com.dieski.data.repository.mapper

import com.dieski.domain.model.SnowQualitySurveyResult
import com.dieski.remote.model.response.SnowQualitySurveyResultResponse

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
fun SnowQualitySurveyResultResponse.toDomain() = SnowQualitySurveyResult(
	resortId = this.resortId,
	totalVotes = this.totalVotes,
	positiveVotes = this.positiveVotes,
	status = this.status
)