package com.dieski.data.datasource

import kotlinx.coroutines.flow.Flow

/**
 *
 * @author   JGeun
 * @created  2024/11/30
 */
interface BookmarkDataSource {
	val bookmarkedResortIdSet: Flow<Set<Long>>

	suspend fun saveResortBookmark(resortId: Long)

	suspend fun deleteResortBookmark(resortId: Long)
}