package com.dieski.weski

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import com.dieski.analytics.AnalyticsLogger
import com.dieski.remote.monitor.NetworkMonitor
import com.dieski.weski.component.WeSkiApp
import com.dieski.weski.navigation.rememberMainNavigator
import com.dieski.weski.presentation.LocalLoggerOwner
import com.dieski.weski.presentation.core.notification.requestInternalNotificationPermission
import com.dieski.weski.presentation.ui.theme.WeskiTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
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
//			getFCMToken()
//			var showRationale by remember { mutableStateOf(false) }
//
//			val launcher = rememberLauncherForActivityResult(
//				contract = ActivityResultContracts.RequestPermission()
//			) { granted ->
////				onPermissionResult(granted)
//				if (!granted && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//					// 사용자가 ‘다시 묻지 않음’ 또는 거부한 경우 라쇼날 여부 확인
//					showRationale = ActivityCompat.shouldShowRequestPermissionRationale(
//						this@MainActivity,
//						Manifest.permission.POST_NOTIFICATIONS
//					).not()
//				}
//			}
//
//			LaunchedEffect(Unit) {
//				requestInternalNotificationPermission(
//					this@MainActivity,
//					launcher,
//					onPermissionResult =  {},
//				)
//			}
//
//			LaunchedEffect(showRationale) {
//				if (showRationale) {
//
//				}
//			}

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

	private fun getFCMToken() {
		FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
			if (!task.isSuccessful) {
				Log.w("Test@@@", "FCM 토큰 가져오기 실패", task.exception)
				return@OnCompleteListener
			}

			// FCM 등록 토큰 가져오기
			val token = task.result

			Log.d("Test@@@", "FCM 토큰: $token")
			Toast.makeText(this, "FCM 토큰이 로그에 출력되었습니다.", Toast.LENGTH_SHORT).show()

			// 서버로 토큰 전송하는 로직을 여기에 추가
			sendTokenToServer(token)
		})
	}

	private fun sendTokenToServer(token: String) {
		// TODO: 여기에 서버로 토큰을 전송하는 로직 구현
		// 예: Retrofit을 사용한 API 호출
		Log.d("Test@@@", "서버로 토큰 전송 필요: $token")
	}
}