package com.dieski.weski.presentation.core.util.flow

import kotlinx.coroutines.flow.SharingCommand

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
sealed interface StartedRestartableCommandProvider {

	fun commands(): List<SharingCommand>

	data object StopAndReset : StartedRestartableCommandProvider {
		override fun commands() = listOf(
			SharingCommand.STOP_AND_RESET_REPLAY_CACHE,
			SharingCommand.START
		)
	}

	data object Stop : StartedRestartableCommandProvider {
		override fun commands() = listOf(
			SharingCommand.START,
			SharingCommand.STOP
		)
	}
}