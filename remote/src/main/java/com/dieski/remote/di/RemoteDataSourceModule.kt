package com.dieski.remote.di

import com.dieski.data.datasource.remote.PlatformConfigRemoteDataSource
import com.dieski.data.datasource.remote.ResortRemoteDataSource
import com.dieski.data.datasource.remote.ResortSnowSurveyRemoteDataSource
import com.dieski.remote.datasource.RemotePlatformConfigDataSourceImpl
import com.dieski.remote.datasource.ResortSnowSurveyRemoteDataSourceImpl
import com.dieski.remote.datasource.ResortRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author   JGeun
 * @created  2025/03/05
 */
@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {

	@Binds
	@Singleton
	abstract fun bindResortSnowSurveyRemoteDataSource(
		source: ResortSnowSurveyRemoteDataSourceImpl
	): ResortSnowSurveyRemoteDataSource

	@Binds
	@Singleton
	abstract fun bindResortRemoteDataSource(
		source: ResortRemoteDataSourceImpl
	): ResortRemoteDataSource

	@Binds
	@Singleton
	fun bindPlatformConfigRemoteDataSource(
		source: RemotePlatformConfigDataSourceImpl
	): PlatformConfigRemoteDataSource
}