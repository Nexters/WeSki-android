package com.dieski.weski.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.dieski.weski.presentation.ui.theme.WeskiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		WindowCompat.setDecorFitsSystemWindows(window, false)

		setContent {
			val navigator: MainNavigator = rememberMainNavigator()

			WeskiTheme {
				MainScreen(
					navigator = navigator
				)
			}
		}
	}
}