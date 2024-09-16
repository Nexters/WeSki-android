package com.dieski.domain.model

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
data class SnowMakingSurveyResult(
	val totalNum: Int = 0,
	val likeNum: Int = 0
) {
	companion object {
		val EMPTY = SnowMakingSurveyResult(0, 0)
	}
}