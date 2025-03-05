package com.dieski.local.datasource

import com.dieski.data.datasource.local.ResortLocalDataSource
import com.dieski.local.datastore.BookmarkedResortDataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2024/11/29
 */
class ResortLocalDataSourceImpl @Inject constructor(
	private val bookmarkedResortDataStoreManager: BookmarkedResortDataStoreManager
): ResortLocalDataSource {

	override val bookmarkedResortIdSet: Flow<Set<Long>> = bookmarkedResortDataStoreManager.bookmarkedResortIdSet

	override suspend fun saveResortBookmark(resortId: Long) {
		bookmarkedResortDataStoreManager.saveResortBookmark(resortId)
	}

	override suspend fun deleteResortBookmark(resortId: Long) {
		bookmarkedResortDataStoreManager.deleteResortBookmark(resortId)
	}
}