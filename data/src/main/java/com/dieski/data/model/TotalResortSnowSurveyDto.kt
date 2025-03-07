package com.dieski.data.model

import com.dieski.data.DataMapper
import com.dieski.domain.model.TotalResortSnowQualitySurvey

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
) : DataMapper<TotalResortSnowQualitySurvey> {
	override fun toDomain(): TotalResortSnowQualitySurvey {
		return TotalResortSnowQualitySurvey(
			resortId = resortId,
			totalVotes = totalVotes,
			positiveVotes = positiveVotes,
			status = status
		)
	}
}