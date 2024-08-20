package com.dieski.data.remote.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
@Serializable
data class SnowQualitySurveyResultResponse(
	@SerialName("totalNum")
	val totalNum: Int,
	@SerialName("likeNum")
	val likeNum: Int,
)