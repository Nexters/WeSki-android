package com.dieski.data.model

import com.dieski.data.DataMapper
import com.dieski.domain.model.ResortSnowMakerSurveyRecord

/**
 * @author   JGeun
 * @created  2025/03/05
 */
data class MyResortSnowSurveyDto(
	val resortId: Long,
	val submitDate: String
) : DataMapper<ResortSnowMakerSurveyRecord> {
	override fun toDomain(): ResortSnowMakerSurveyRecord {
		return ResortSnowMakerSurveyRecord(
			resortId = resortId,
			submitDate = submitDate
		)
	}
}