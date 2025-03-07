package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
data class TotalResortSnowQualitySurvey(
	val resortId: Long,
	val totalVotes: Int,
	val positiveVotes: Int,
	val status: String
) {
	companion object {
		val EMPTY = TotalResortSnowQualitySurvey(-1, 0, 0, "")
	}
}