package com.dieski.data.repository.di

import com.dieski.data.repository.DefaultWeskiRepository
import com.dieski.data.repository.FakeWeskiRepository
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
	@Named("weski")
	abstract fun bindsWeSkiRepository(
		defaultWeskiRepository: DefaultWeskiRepository
	): WeSkiRepository

	@Binds
	@Singleton
	@Named("fake")
	abstract fun bindsFakeWeSkiRepository(
		fakeWeskiRepository: FakeWeskiRepository
	): WeSkiRepository
}