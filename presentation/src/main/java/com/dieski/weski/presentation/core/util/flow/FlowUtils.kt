package com.dieski.weski.presentation.core.util.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
fun <T> Flow<T>.restartableStateIn(
	scope: CoroutineScope,
	started: SharingStarted,
	initialValue: T,
	restartableCommandProvider: StartedRestartableCommandProvider = StartedRestartableCommandProvider.StopAndReset
) : RestartableStateFlow<T> {
	val sharingStarted: StartedRestartable = StartedRestartableImpl(
		started,
		restartableCommandProvider
	)

	val state = stateIn(scope, sharingStarted, initialValue)

	return RestartableStateFlowImpl(state, sharingStarted)
}