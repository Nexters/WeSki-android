package com.dieski.data.repository

import com.dieski.data.datasource.BookmarkDataSource
import com.dieski.data.datasource.WeSkiDataSource
import com.dieski.data.datasource.di.Local
import com.dieski.data.datasource.di.Remote
import com.dieski.domain.model.SkiResortInfo
import com.dieski.domain.model.SkiResortWeatherInfo
import com.dieski.domain.repository.WeSkiRepository
import com.dieski.domain.dispatchers.Dispatcher
import com.dieski.domain.dispatchers.WeSkiDispatchers
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
	@Remote private val remoteWeSkiDataSource: WeSkiDataSource,
	@Local private val localBookmarkDataSource: BookmarkDataSource,
	@Dispatcher(WeSkiDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : WeSkiRepository {

	override suspend fun fetchAllSkiResortsInfo(): WResult<List<SkiResortInfo>, WError> {
		return withContext(ioDispatcher) {
			val bookmarkedResortIdSet = localBookmarkDataSource.bookmarkedResortIdSet.first()
			when (val result = remoteWeSkiDataSource.fetchAllSkiResortsInfo()) {
				is WResult.Success -> {
					withContext(Dispatchers.Default) {
						val data = result.data.map {
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
				is WResult.Error -> result
			}
		}
	}

	override suspend fun fetchSkiResortWeatherInfo(resortId: Long): WResult<SkiResortWeatherInfo, WError> {
		return withContext(ioDispatcher) {
			remoteWeSkiDataSource.fetchSkiResortWeatherInfo(resortId)
		}
	}

	override suspend fun saveResortBookmark(resortId: Long) {
		withContext(ioDispatcher) {
			localBookmarkDataSource.saveResortBookmark(resortId)
		}
	}

	override suspend fun deleteResortBookmark(resortId: Long) {
		withContext(ioDispatcher) {
			localBookmarkDataSource.deleteResortBookmark(resortId)
		}
	}
}