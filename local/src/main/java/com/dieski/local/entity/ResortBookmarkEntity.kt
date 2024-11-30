package com.dieski.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author   JGeun
 * @created  2024/11/29
 */
@Entity(tableName = "ResortBookmark")
data class ResortBookmarkEntity(
	@PrimaryKey
	@ColumnInfo("resort_id")
	val resortId: Long,
	@ColumnInfo("bookmarked_date")
	val bookmarkedDate: String,
	@ColumnInfo("bookmarked")
	val isBookmarked: Boolean
)