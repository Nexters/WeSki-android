package com.dieski.domain.model

import com.dieski.domain.model.platform.PlatformType

/**
 *
 * @author   JGeun
 * @created  2025/02/09
 */
data class PlatformUpdateCheckResult(
	val platformType: PlatformType = PlatformType.ANDROID,
	val minVersion: String,
	val checkForceUpdate: Boolean
)