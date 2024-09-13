package com.dieski.remote.di

import com.dieski.remote.service.SnowQualityService
import com.dieski.remote.service.WeSkiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 *
 * @author   JGeun
 * @created  2024/09/13
 */
@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

	@Provides
	@Singleton
	fun provideWeSkiApi(
		@DefaultApi retrofit: Retrofit
	): WeSkiService {
		return retrofit.create(WeSkiService::class.java)
	}

	@Provides
	@Singleton
	fun provideSnowQualityService(
		@DefaultApi retrofit: Retrofit
	): SnowQualityService {
		return retrofit.create(SnowQualityService::class.java)
	}
}