package com.dieski.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dieski.local.dao.ResortSnowMakerSurveyRecordDao
import com.dieski.local.entity.ResortSnowMakerSurveyRecordEntity

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
@Database(
	entities = [ResortSnowMakerSurveyRecordEntity::class],
	version = 1
)
abstract class WeSkiDatabase :RoomDatabase() {
	abstract fun resortSnowMakerSurveyRecordDao(): ResortSnowMakerSurveyRecordDao

	companion object {
		const val DATABASE_NAME = "weski.db"
	}
}