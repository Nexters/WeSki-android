package com.dieski.domain.model

import com.dieski.domain.util.DateUtil

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
data class ResortSnowMakerSurveyRecord(
	val resortId: Long,
	val submitDate: String
) {
	fun checkSubmitDateIsToday(): Boolean {
		return submitDate == DateUtil.createYYYYMMDDFormat()
	}
}