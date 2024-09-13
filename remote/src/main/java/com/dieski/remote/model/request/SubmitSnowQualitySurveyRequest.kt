package com.dieski.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author   JGeun
 * @created  2024/08/18
 */
@Serializable
data class SubmitSnowQualitySurveyRequest(
	@SerialName("isLike")
	val isLike: Boolean
)