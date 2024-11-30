package com.dieski.local.di

import android.content.Context
import com.dieski.local.datastore.BookmarkedResortDataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author   JGeun
 * @created  2024/11/29
 */
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {


	@Singleton
	@Provides
	fun providesResortBookmarkDataStoreManager(
		@ApplicationContext applicationContext: Context
	) : BookmarkedResortDataStoreManager {
		return BookmarkedResortDataStoreManager(applicationContext)
	}
}