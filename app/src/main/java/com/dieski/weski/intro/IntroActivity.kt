package com.dieski.weski.intro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.dieski.weski.IntroScreen
import com.dieski.weski.MainActivity
import com.dieski.weski.presentation.ui.theme.WeskiTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * @author   JGeun
 * @created  2024/08/06
 */
private const val SPLASH_TIME = 1000L

@AndroidEntryPoint
class IntroActivity : ComponentActivity()  {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		WindowCompat.setDecorFitsSystemWindows(window, false)

		setContent {
			val context = LocalContext.current

			LaunchedEffect(Unit) {
				delay(SPLASH_TIME)
				this.launch(Dispatchers.Main) {
					startActivity(
						Intent(context, MainActivity::class.java).apply {
							finish()
						}
					)
				}
			}

			WeskiTheme {
				IntroScreen()
			}
		}
	}
}