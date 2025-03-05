package com.dieski.local.di

import android.content.Context
import androidx.room.Room
import com.dieski.local.room.RoomConstant
import com.dieski.local.room.dao.ResortSnowSurveyDao
import com.dieski.local.room.WeSkiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

	@Singleton
	@Provides
	fun provideWeSkiDatabase(
		@ApplicationContext context: Context
	): WeSkiDatabase =
		Room.databaseBuilder(
			context,
			WeSkiDatabase::class.java,
			RoomConstant.ROOM_DB_NAME
		).fallbackToDestructiveMigration()
			.build()

	@Singleton
	@Provides
	fun provideResortSnowSurveyDao(
		weSkiDatabase: WeSkiDatabase
	): ResortSnowSurveyDao = weSkiDatabase.resortSnowSurveyDao()
}