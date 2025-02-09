package com.dieski.domain.repository

import com.dieski.domain.model.PlatformUpdateCheckResult
import com.dieski.domain.model.platform.PlatformVersion
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author   JGeun
 * @created  2025/02/10
 */
interface PlatformConfigRepository {

	fun checkPlatformVersionForForcedUpdate(
		platformVersion: PlatformVersion
	): Flow<PlatformUpdateCheckResult?>
}