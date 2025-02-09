package com.dieski.data.repository

import com.dieski.data.datasource.RemotePlatformConfigDataSource
import com.dieski.data.datasource.di.Remote
import com.dieski.data.repository.mapper.toDomain
import com.dieski.domain.dispatchers.Dispatcher
import com.dieski.domain.dispatchers.WeSkiDispatchers
import com.dieski.domain.model.PlatformUpdateCheckResult
import com.dieski.domain.model.platform.PlatformVersion
import com.dieski.domain.repository.PlatformConfigRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/02/10
 */
class DefaultPlatformConfigRepository @Inject constructor(
	@Remote private val remotePlatformConfigDataSource: RemotePlatformConfigDataSource,
	@Dispatcher(WeSkiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : PlatformConfigRepository {

	override fun checkPlatformVersionForForcedUpdate(
		platformVersion: PlatformVersion
	): Flow<PlatformUpdateCheckResult?> =
		remotePlatformConfigDataSource.checkPlatformVersionForForcedUpdate(platformVersion)
			.map { it?.toDomain() }
			.flowOn(ioDispatcher)
}