package com.dieski.remote.model.response

import com.dieski.domain.model.platform.PlatformType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author   JGeun
 * @created  2025/02/09
 */
@Serializable
data class PlatformForceUpdateCheckResult(
	@SerialName("platform")
	val platform: String = PlatformType.ANDROID.value,
	@SerialName("minVersion")
	val minVersion: String = "1.0.0",
	@SerialName("isForceUpdate")
	val isForceUpdate: Boolean = false
)