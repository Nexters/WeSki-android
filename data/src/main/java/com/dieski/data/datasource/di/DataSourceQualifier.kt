package com.dieski.data.datasource.di

import javax.inject.Qualifier

/**
 *
 * @author   JGeun
 * @created  2024/11/12
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Remote

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Local