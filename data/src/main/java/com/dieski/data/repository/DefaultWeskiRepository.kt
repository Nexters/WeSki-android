package com.dieski.data.repository

import com.dieski.data.dispatchers.Dispatcher
import com.dieski.data.dispatchers.WeSkiDispatchers
import com.dieski.domain.repository.WeSkiRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
internal class DefaultWeskiRepository @Inject constructor(
	@Dispatcher(WeSkiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : WeSkiRepository {

	override fun fetchResortWeatherInfoList() {
			return
	}
}