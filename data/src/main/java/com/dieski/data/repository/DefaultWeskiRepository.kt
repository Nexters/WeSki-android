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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
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

	override fun getSkiResortList(): Flow<List<SkiResortInfo>> = combine(
		resortRemoteDataSource.getSkiResortList(),
		resortLocalDataSource.bookmarkedResortIdSet
	) {  skiResortList, bookmarkedIdSet ->
		skiResortList.toDomain().map {
			it.copy(
				isBookmarked = bookmarkedIdSet.contains(it.resortId)
			)
		}.sortedBy {
			val index = bookmarkedIdSet.indexOf(it.resortId)
			if (index != -1) index else Int.MAX_VALUE
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