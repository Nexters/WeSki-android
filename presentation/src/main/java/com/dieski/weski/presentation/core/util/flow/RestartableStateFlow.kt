package com.dieski.weski.presentation.core.util.flow

import kotlinx.coroutines.flow.StateFlow

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
interface RestartableStateFlow<out T> :StateFlow<T> {
	fun restart()
}

internal class RestartableStateFlowImpl<T>(
	flow: StateFlow<T>,
	private val started: StartedRestartable,
) : RestartableStateFlow<T>, StateFlow<T> by flow {

	override fun restart() {
		started.restart()
	}
}