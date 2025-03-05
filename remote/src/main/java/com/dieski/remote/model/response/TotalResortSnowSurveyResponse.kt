package com.dieski.remote.model.response

import com.dieski.data.model.TotalResortSnowSurveyDto
import com.dieski.remote.RemoteMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
@Serializable
data class TotalResortSnowSurveyResponse(
	@SerialName("resortId")
	val resortId: Long,
	@SerialName("totalVotes")
	val totalVotes: Int,
	@SerialName("positiveVotes")
	val positiveVotes: Int,
	@SerialName("status")
	val status: String
) : RemoteMapper<TotalResortSnowSurveyDto> {

	override fun toData() = TotalResortSnowSurveyDto(
		resortId = resortId,
		totalVotes = totalVotes,
		positiveVotes = positiveVotes,
		status = status
	)
}