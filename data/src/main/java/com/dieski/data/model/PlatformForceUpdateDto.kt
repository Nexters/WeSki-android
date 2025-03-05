package com.dieski.data.model

import com.dieski.data.DataMapper
import com.dieski.domain.model.PlatformUpdateCheckResult
import com.dieski.domain.model.platform.PlatformType

/**
 *
 * @author   JGeun
 * @created  2025/03/05
 */
data class PlatformForceUpdateDto(
	val platform: String = PlatformType.ANDROID.value,
	val minVersion: String = "1.0.0",
	val isForceUpdate: Boolean = false
): DataMapper<PlatformUpdateCheckResult> {
	override fun toDomain(): PlatformUpdateCheckResult {
		return PlatformUpdateCheckResult(
			minVersion = minVersion,
			checkForceUpdate = isForceUpdate,
			platformType = PlatformType.findByValue(platform)
		)
	}
}