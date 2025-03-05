package com.dieski.data.repository

import com.dieski.data.datasource.local.ResortLocalDataSource
import com.dieski.data.datasource.remote.ResortRemoteDataSource
import com.dieski.data.toDomain
import com.dieski.data.toDomainModel
import com.dieski.domain.dispatchers.Dispatcher
import com.dieski.domain.dispatchers.WeSkiDispatchers
import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.SkiResortWeatherInfo
import com.dieski.domain.repository.WeSkiRepository
import com.dieski.domain.result.WError
import com.dieski.domain.result.WResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
internal class DefaultWeskiRepository @Inject constructor(
	private val resortRemoteDataSource: ResortRemoteDataSource,
	private val resortLocalDataSource: ResortLocalDataSource,
	@Dispatcher(WeSkiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : WeSkiRepository {

	override suspend fun fetchAllSkiResortsInfo(): WResult<List<SkiResortInfo>, WError> {
		return withContext(ioDispatcher) {
			val bookmarkedResortIdSet = resortLocalDataSource.bookmarkedResortIdSet.first()
			when (val result = resortRemoteDataSource.fetchAllSkiResortsInfo()) {
				is WResult.Success -> {
					withContext(Dispatchers.Default) {
						val data = result.data.toDomain().map {
							it.copy(
								isBookmarked = bookmarkedResortIdSet.contains(it.resortId)
							)
						}.sortedBy {
							val index = bookmarkedResortIdSet.indexOf(it.resortId)
							if (index != -1) index else Int.MAX_VALUE
						}

						WResult.Success(data)
					}
				}

				is WResult.Error -> result.toDomainModel()
			}
		}
	}

	override suspend fun fetchSkiResortWeatherInfo(resortId: Long): WResult<SkiResortWeatherInfo, WError> {
		return withContext(ioDispatcher) {
			when (val result = resortRemoteDataSource.fetchSkiResortWeatherInfo(resortId)) {
				is WResult.Success -> {
					WResult.Success(result.data.toDomain())
				}

				is WResult.Error -> result.toDomainModel()
			}
		}
	}

	override suspend fun saveResortBookmark(resortId: Long) {
		withContext(ioDispatcher) {
			resortLocalDataSource.saveResortBookmark(resortId)
		}
	}

	override suspend fun deleteResortBookmark(resortId: Long) {
		withContext(ioDispatcher) {
			resortLocalDataSource.deleteResortBookmark(resortId)
		}
	}
}