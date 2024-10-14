package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
data class SnowQualitySurveyResult(
	val resortId: Long,
	val totalVotes: Int,
	val positiveVotes: Int,
	val status: String
) {
	companion object {
		val EMPTY = SnowQualitySurveyResult(-1, 0, 0, "")
	}
}