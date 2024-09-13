package com.dieski.analytics

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
private val analyticsExcpetionHandler =
	CoroutineExceptionHandler { coroutineContext, throwable ->
		Timber.e(throwable)
	}
internal val analyticsScope =
	CoroutineScope(SupervisorJob() + Dispatchers.IO + analyticsExcpetionHandler)