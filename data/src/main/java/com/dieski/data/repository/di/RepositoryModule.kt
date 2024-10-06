package com.dieski.data.repository.di

import com.dieski.data.repository.DefaultSnowQualityRepository
import com.dieski.data.repository.DefaultWeskiRepository
import com.dieski.domain.repository.SnowQualityRepository
import com.dieski.domain.repository.WeSkiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 *
 * @author   JGeun
 * @created  2024/08/13
 */
@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

	@Binds
	@Singleton
	abstract fun bindsWeSkiRepository(
		defaultWeskiRepository: DefaultWeskiRepository
	): WeSkiRepository

	@Binds
	@Singleton
	abstract fun bindsSnowQualityRepository(
		defaultSnowQualityRepository: DefaultSnowQualityRepository
	): SnowQualityRepository
}