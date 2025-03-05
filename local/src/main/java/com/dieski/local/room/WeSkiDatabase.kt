package com.dieski.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dieski.local.room.dao.ResortSnowSurveyDao
import com.dieski.local.entity.ResortSnowSurveyEntity

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
@Database(
	entities = [ResortSnowSurveyEntity::class],
	version = RoomConstant.ROOM_VERSION
)
abstract class WeSkiDatabase :RoomDatabase() {
	abstract fun resortSnowSurveyDao(): ResortSnowSurveyDao
}