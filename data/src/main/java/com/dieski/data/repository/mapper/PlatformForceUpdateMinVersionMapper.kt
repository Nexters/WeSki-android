package com.dieski.data.repository.mapper

import com.dieski.domain.model.PlatformUpdateCheckResult
import com.dieski.domain.model.platform.PlatformType
import com.dieski.remote.model.response.PlatformForceUpdateCheckResult

/**
 *
 * @author   JGeun
 * @created  2025/02/09
 */
fun PlatformForceUpdateCheckResult.toDomain() = PlatformUpdateCheckResult(
	minVersion = minVersion,
	checkForceUpdate = isForceUpdate,
	platformType = PlatformType.findByValue(platform)
)