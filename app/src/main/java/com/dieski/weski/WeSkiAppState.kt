package com.dieski.weski

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.dieski.analytics.AnalyticsLogger
import com.dieski.remote.monitor.NetworkMonitor
import com.dieski.weski.navigation.MainNavigator
import com.dieski.weski.navigation.rememberMainNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
internal fun rememberWeSkiAppState(
	networkMonitor: NetworkMonitor,
	logger: AnalyticsLogger,
	coroutineScope: CoroutineScope = rememberCoroutineScope(),
	navigator: MainNavigator = rememberMainNavigator()
) = remember(
    networkMonitor,
	logger,
    coroutineScope,
    navigator
) {
    WeSkiAppState(
        networkMonitor = networkMonitor,
		logger = logger,
        coroutineScope = coroutineScope,
        navigator = navigator
    )
}

@Stable
internal class WeSkiAppState(
	val networkMonitor: NetworkMonitor,
	val logger: AnalyticsLogger,
	val coroutineScope: CoroutineScope,
	val navigator: MainNavigator
) {
    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )
}