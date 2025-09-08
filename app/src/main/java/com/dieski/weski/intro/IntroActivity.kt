package com.dieski.weski.intro

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dieski.weski.BuildConfig
import com.dieski.weski.MainActivity
import com.dieski.weski.component.ForceUpdateDialog
import com.dieski.weski.notification.createInterruptNotificationChannel
import com.dieski.weski.notification.createNotificationChannel
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.noRippleClickable
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

	private val viewModel by viewModels<IntroViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		configureAppStartup()

		setContent {
			val context = LocalContext.current
			val checkPlatformVersionForForcedUpdate by viewModel.checkRequiredForceUpdateVersion.collectAsStateWithLifecycle()
			var didSplashJobSucceed by rememberSaveable  { mutableStateOf(false) }
			var showForceUpdateDialog by rememberSaveable { mutableStateOf(false) }

			LaunchedEffect(Unit) {
				delay(SPLASH_TIME)
				didSplashJobSucceed = true
			}

			LaunchedEffect(didSplashJobSucceed, checkPlatformVersionForForcedUpdate) {
				if (didSplashJobSucceed.not() || checkPlatformVersionForForcedUpdate == null) return@LaunchedEffect

				// 강제 업데이트가 필요한 경우
				if (checkPlatformVersionForForcedUpdate == true) {
					showForceUpdateDialog = true
					return@LaunchedEffect
				} else {
					this.launch(Dispatchers.Main.immediate) {
						startActivity(
							Intent(context, MainActivity::class.java).apply {
								addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
								finish()
							}
						)
					}
				}
			}

			WeskiTheme {
				Box(
					modifier = Modifier.fillMaxSize()
				) {
					IntroScreen()

					if (showForceUpdateDialog) {
						ForceUpdateDialog(
							openPlayStoreUrl = {
								val playStoreUrl =
									"http://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"
								startActivity(
									Intent(Intent.ACTION_VIEW).apply {
										data = Uri.parse(playStoreUrl)
										setPackage("com.android.vending")
									}
								)
							}
						)
					}
				}
			}
		}
	}

	private fun configureAppStartup() {
		WindowCompat.setDecorFitsSystemWindows(window, false)

		setupNotification()
	}

	private fun setupNotification() {
		createNotificationChannel()
		createInterruptNotificationChannel()
	}
}