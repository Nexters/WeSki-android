package com.dieski.local.di

import android.content.Context
import androidx.room.Room
import com.dieski.local.dao.ResortSnowMakerSurveyRecordDao
import com.dieski.local.db.WeSkiDatabase
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
	fun providesWeSkiDatabase(
		@ApplicationContext context: Context
	): WeSkiDatabase =
		Room.databaseBuilder(context, WeSkiDatabase::class.java, WeSkiDatabase.DATABASE_NAME)
			.build()

	@Singleton
	@Provides
	fun providesSnowMakerSurveyRecordDao(
		weSkiDatabase: WeSkiDatabase
	): ResortSnowMakerSurveyRecordDao = weSkiDatabase.resortSnowMakerSurveyRecordDao()
}