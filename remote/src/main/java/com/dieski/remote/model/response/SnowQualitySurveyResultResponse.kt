package com.dieski.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
@Serializable
data class SnowQualitySurveyResultResponse(
	@SerialName("resortId")
	val resortId: Long,
	@SerialName("totalVotes")
	val totalVotes: Int,
	@SerialName("positiveVotes")
	val positiveVotes: Int,
	@SerialName("status")
	val status: String
)