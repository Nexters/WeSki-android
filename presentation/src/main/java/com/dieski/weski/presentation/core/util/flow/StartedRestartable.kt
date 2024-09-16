package com.dieski.weski.presentation.core.util.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingCommand
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.merge

/**
 *
 * @author   JGeun
 * @created  2024/09/14
 */
interface StartedRestartable : SharingStarted {
	fun restart()
}

internal class StartedRestartableImpl(
	private val sharingStarted: SharingStarted,
	private val provider: StartedRestartableCommandProvider
) : StartedRestartable {

	private val flow = MutableSharedFlow<SharingCommand>(extraBufferCapacity = 2)

	override fun restart() {
		provider.commands().forEach {
			flow.tryEmit(it)
		}
	}

	override fun command(subscriptionCount: StateFlow<Int>): Flow<SharingCommand> {
		return merge(flow, sharingStarted.command(subscriptionCount))
	}
}