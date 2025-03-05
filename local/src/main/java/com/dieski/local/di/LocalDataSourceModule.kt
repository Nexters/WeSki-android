package com.dieski.local.di

import com.dieski.data.datasource.local.ResortLocalDataSource
import com.dieski.data.datasource.local.ResortSnowSurveyLocalDataSource
import com.dieski.local.datasource.ResortLocalDataSourceImpl
import com.dieski.local.datasource.ResortSnowSurveyLocalDataSourceIImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author   JGeun
 * @created  2025/03/05
 */

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {

	@Binds
	@Singleton
	fun bindsLocalBookmarkDataSource(
		source: ResortLocalDataSourceImpl
	): ResortLocalDataSource

	@Binds
	@Singleton
	abstract fun bindsLocalSnowQualityDataSource(
		source: ResortSnowSurveyLocalDataSourceIImpl
	): ResortSnowSurveyLocalDataSource
}