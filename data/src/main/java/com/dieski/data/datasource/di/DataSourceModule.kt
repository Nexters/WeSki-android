package com.dieski.data.datasource.di

import com.dieski.data.datasource.LocalSnowMakerDataSource
import com.dieski.data.datasource.RemoteSnowQualityDataSource
import com.dieski.data.datasource.RemoteWeSkiDataSource
import com.dieski.data.datasource.SnowMakerSurveyRecordDataSource
import com.dieski.data.datasource.SnowQualityDataSource
import com.dieski.data.datasource.WeSkiDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

	@Binds
	@Singleton
	@Remote
	abstract fun bindsRemoteSnowQualityDataSource(
		remoteSnowQualityDataSource: RemoteSnowQualityDataSource
	): SnowQualityDataSource

	@Binds
	@Singleton
	@Remote
	abstract fun bindsRemoteWeSkiDataSource(
		remoteWeSkiDataSource: RemoteWeSkiDataSource
	): WeSkiDataSource

	@Binds
	@Singleton
	@Local
	abstract fun bindsLocalSnowQualityDataSource(
		localSnowMakerDataSource: LocalSnowMakerDataSource
	): SnowMakerSurveyRecordDataSource
}