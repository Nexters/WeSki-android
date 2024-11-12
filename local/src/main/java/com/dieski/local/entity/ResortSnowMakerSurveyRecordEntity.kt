package com.dieski.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
@Entity(tableName = "ResortSnowMakerSurveyRecord")
data class ResortSnowMakerSurveyRecordEntity(
	@PrimaryKey val resortId: Long,
	val submitDate: String
)