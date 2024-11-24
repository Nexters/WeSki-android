package com.dieski.weski

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import com.dieski.analytics.AnalyticsLogger
import com.dieski.analytics.FireBaseAnalyticsLogger
import com.dieski.remote.monitor.NetworkMonitor
import com.dieski.weski.component.WeSkiApp
import com.dieski.weski.navigation.rememberMainNavigator
import com.dieski.weski.presentation.LocalLoggerOwner
import com.dieski.weski.presentation.ui.theme.WeskiTheme
import com.dieski.weski.presentation.util.log
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	@Inject
	lateinit var networkMonitor: NetworkMonitor

	@Inject
	lateinit var logger: AnalyticsLogger


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		WindowCompat.setDecorFitsSystemWindows(window, false)

		setContent {
			val weSkiAppState = rememberWeSkiAppState(
				networkMonitor = networkMonitor,
				logger = logger,
				navigator = rememberMainNavigator(),
			)

			CompositionLocalProvider(
				LocalLoggerOwner provides logger,
			) {
				WeskiTheme {
					WeSkiApp(
						appState = weSkiAppState,
					)
				}
			}
		}
	}
}