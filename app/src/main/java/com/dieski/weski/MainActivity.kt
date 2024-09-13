package com.dieski.weski

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.dieski.remote.monitor.NetworkMonitor
import com.dieski.weski.component.WeSkiApp
import com.dieski.weski.presentation.ui.theme.WeskiTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	@Inject
	lateinit var networkMonitor: NetworkMonitor

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		WindowCompat.setDecorFitsSystemWindows(window, false)

		setContent {
			val weSkiAppState = rememberWeSkiAppState(
				networkMonitor = networkMonitor
			)

			WeskiTheme {
				WeSkiApp(
					appState = weSkiAppState
				)
			}
		}
	}
}