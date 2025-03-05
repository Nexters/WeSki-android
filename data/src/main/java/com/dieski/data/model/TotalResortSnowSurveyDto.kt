package com.dieski.data.model

import com.dieski.data.DataMapper
import com.dieski.domain.model.SnowQualitySurveyResult

/**
 *
 * @author   JGeun
 * @created  2025/03/05
 */
data class TotalResortSnowSurveyDto(
	val resortId: Long,
	val totalVotes: Int,
	val positiveVotes: Int,
	val status: String
) : DataMapper<SnowQualitySurveyResult> {
	override fun toDomain(): SnowQualitySurveyResult {
		return SnowQualitySurveyResult(
			resortId = resortId,
			totalVotes = totalVotes,
			positiveVotes = positiveVotes,
			status = status
		)
	}
}