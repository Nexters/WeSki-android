package com.dieski.weski.presentation.detail.component

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor.Gray100
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor.Gray60
import com.dieski.weski.presentation.core.notification.NotificationPermissionState
import com.dieski.weski.presentation.core.notification.requestInternalNotificationPermission
import com.dieski.weski.presentation.core.util.debounceClickable
import com.dieski.weski.presentation.core.util.findActivity
import com.dieski.weski.presentation.core.util.noRippleClickable
import com.dieski.weski.presentation.ui.theme.WeskiTheme
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

/**
 * @author   JGeun
 * @created  2025/11/22
 */
@Composable
internal fun DetailResortOpenNotificationDialog(
	onDismiss: () -> Unit,
	onShowPermissionSuccessDialog: () -> Unit,
	modifier: Modifier = Modifier
) {
	val context = LocalContext.current
	val lifecycleOwner = LocalLifecycleOwner.current
	val findActivity = context.findActivity()

	var isNotiPermissionGranted by rememberSaveable { mutableStateOf(false) }
	var showUserNotificationPermission by remember { mutableStateOf(false) }
	var showRationale by remember { mutableStateOf(false) }

	val launcher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.RequestPermission()
	) { granted ->
		if (granted) {
			isNotiPermissionGranted = true
			onDismiss()
			onShowPermissionSuccessDialog()
		}
		if (!granted && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			// 사용자가 ‘다시 묻지 않음’ 또는 거부한 경우 라쇼날 여부 확인
			showRationale = findActivity?.let { activity ->
				ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.POST_NOTIFICATIONS).not()
			} ?: false
		}
	}

	LaunchedEffect(showUserNotificationPermission) {
		if (showUserNotificationPermission) {
			if (findActivity != null) {
				requestInternalNotificationPermission(
					findActivity,
					launcher,
					onPermissionResult = { state ->
						when (state) {
							is NotificationPermissionState.Granted -> {
								isNotiPermissionGranted = true
								onShowPermissionSuccessDialog()
								onDismiss()
							}
							else -> {}
						}
					},
				)
			} else {
				showRationale = true
			}
			showUserNotificationPermission = false
		}
	}

	LaunchedEffect(showRationale) {
		if (showRationale) {
			showRationale = true
		}
	}

	DisposableEffect(lifecycleOwner) {
		val observer = LifecycleEventObserver { _, event ->
			if (event == Lifecycle.Event.ON_RESUME) {
				// 설정에서 돌아왔을 때 권한 재확인
				if (context.isNotificationGranted()) {
					isNotiPermissionGranted = true
				} else {
					// 권한이 거부됨
				}
			}
		}
		lifecycleOwner.lifecycle.addObserver(observer)

		onDispose {
			lifecycleOwner.lifecycle.removeObserver(observer)
		}
	}

	Box(
		modifier = modifier
			.fillMaxSize()
			.background(Color.Black.copy(0.5f))
			.noRippleClickable { onDismiss() }
	) {
		DetailResortOpenNotificationDialogContent(
			isPermissionGranted = isNotiPermissionGranted,
			onDismiss = onDismiss,
			onCheckPermission = {
				showUserNotificationPermission = true
			},
			modifier = Modifier
				.align(Alignment.Center)
				.padding(20.dp)
		)

		if (showRationale) {
			PermissionRationalePopup(
				onConfirm = {
					val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
						data = Uri.fromParts("package", context.packageName, null)
					}
					context.startActivity(intent)

					showRationale = false
				},
				onDismiss = {
					showRationale = false
				}
			)
		}
	}
}

private const val SEC = 1000L

@Composable
private fun DetailResortOpenNotificationDialogContent(
	isPermissionGranted: Boolean,
	onDismiss: () -> Unit,
	onCheckPermission: () -> Unit,
	modifier: Modifier = Modifier
) {
	var timeRemaining by remember { mutableLongStateOf(0L) }

	LaunchedEffect(Unit) {
		while (true) {
			val now = LocalDateTime.now()
			val targetDate = LocalDateTime.of(2025, 11, 28, 23, 59, 59)
			val millis = ChronoUnit.MILLIS.between(now, targetDate)
			timeRemaining = if (millis > 0) millis else 0L
			delay(SEC)
		}
	}


	val days = timeRemaining / (1000 * 60 * 60 * 24)
	val hours = (timeRemaining % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
	val minutes = (timeRemaining % (1000 * 60 * 60)) / (1000 * 60)
	val seconds = (timeRemaining % (1000 * 60)) / 1000

	Column(
		modifier = modifier
			.background(color = Color.White, shape = RoundedCornerShape(19.dp))
			.clip(RoundedCornerShape(19.dp))
			.padding(vertical = 24.dp, horizontal = 20.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Row(modifier = Modifier.fillMaxWidth()) {
			Spacer(Modifier.weight(1f))
			Icon(
				modifier = Modifier
					.size(24.dp)
					.noRippleClickable { onDismiss() },
				painter = painterResource(R.drawable.ico_close),
				contentDescription = "close",
				tint = Gray60
			)
			Spacer(Modifier.width(4.dp))
		}

		Spacer(Modifier.height(22.dp))

		Text(
			text = "이 스키장 오픈일까지\n남은 시간이에요",
			style = WeskiTheme.typography.heading2Bold,
			color = WeskiColor.Gray100,
			textAlign = TextAlign.Center
		)

		Spacer(Modifier.height(27.dp))

		Row(
			horizontalArrangement = Arrangement.spacedBy(16.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			CountdownItem(
				value = days,
				label = "days"
			)

			Icon(
				painter = painterResource(R.drawable.ic_open_resort_time_dot_with_padding),
				contentDescription = ""
			)

			CountdownItem(
				value = hours,
				label = "hours"
			)

			Icon(
				painter = painterResource(R.drawable.ic_open_resort_time_dot_with_padding),
				contentDescription = ""
			)

			CountdownItem(
				value = minutes,
				label = "mins"
			)

			Icon(
				painter = painterResource(R.drawable.ic_open_resort_time_dot_with_padding),
				contentDescription = ""
			)

			CountdownItem(
				value = seconds,
				label = "secs"
			)
		}

		Image(
			modifier = Modifier
				.fillMaxWidth()
				.aspectRatio(375 / 269f),
			painter = painterResource(R.drawable.img_open_resort_dialog_center_image),
			contentDescription = ""
		)

		Spacer(Modifier.height(11.dp))

		Text(
			text = "스키장이 오픈 될 때마다 알려드릴게요",
			style = TextStyle(
				fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
				fontWeight = FontWeight.W600,
				fontSize = 15.sp,
				lineHeight = 1.5.em,
				color = Gray60
			),
		)

		Spacer(Modifier.height(16.dp))

		Box(
			modifier = Modifier
				.fillMaxWidth()
				.clip(RoundedCornerShape(8.dp))
				.background(color = WeskiColor.Main01, shape = RoundedCornerShape(8.dp))
				.debounceClickable(debounceTime = 20L) { if(isPermissionGranted.not())  onCheckPermission() }
				.padding(vertical = 14.5.dp, horizontal = 20.dp),
		) {
			Text(
				modifier = Modifier.align(Alignment.Center),
				text = if (isPermissionGranted.not()) "오픈 알림 받기" else "오픈 알림 받기가 설정되어 있습니다.",
				style = TextStyle(
					color = Color.White,
					fontWeight = FontWeight.W700,
					fontFamily = FontFamily(Font(R.font.pretendard_bold)),
					fontSize = 16.sp,
					lineHeight = 1.45.em,
					letterSpacing = (-0.02).em,
				)
			)
		}
	}
}

@Composable
private fun CountdownItem(
	value: Long,
	label: String,
	modifier: Modifier = Modifier
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier
	) {
		Text(
			text = String.format("%02d", value),
			style = TextStyle(
				fontFamily = FontFamily(Font(R.font.pretendard_bold)),
				fontWeight = FontWeight.Bold,
				fontSize = 34.sp,
				lineHeight = 1.4.em,
				color = Gray100
			),
		)
		Text(
			text = label,
			style = TextStyle(
				fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
				fontWeight = FontWeight.Normal,
				fontSize = 10.sp,
				lineHeight = 1.4.em,
				color = Color(0xFF5B6A87)
			),
		)
	}
}

@Composable
private fun PermissionRationalePopup(
	onConfirm: () -> Unit,
	onDismiss: () -> Unit,
	modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier
			.fillMaxSize()
			.background(Gray100.copy(alpha = 0.5f))
			.noRippleClickable { onDismiss() }
	) {
		Column(
			modifier = Modifier
				.widthIn(max = 316.dp)
				.background(color = WeskiColor.White, shape = RoundedCornerShape(20.dp))
				.align(Alignment.Center)
				.padding(top = 38.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
		) {
			Text(
				text = "위스키에서 보내는 알림(push)를 받아보시겠어요?",
				style = WeskiTheme.typography.title2SemiBold,
				color = WeskiColor.Gray100,
				textAlign = TextAlign.Center,
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 26.dp)
			)

			Spacer(Modifier.height(10.dp))

			Text(
				text = "수신동의 시 위스키 오픈 알림 및 다양한\n정보에 대한 알림을 받아보실 수 있습니다.",
				style = WeskiTheme.typography.body1Regular,
				color = WeskiColor.Gray60,
				textAlign = TextAlign.Center,
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 26.dp)
			)

			Spacer(Modifier.height(18.dp))

			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(8.dp)
			) {
				Text(
					modifier = Modifier
						.weight(1f)
						.background(shape = RoundedCornerShape(8.dp), color = WeskiColor.Gray20)
						.noRippleClickable { onDismiss() }
						.padding(vertical = 16.5.dp),
					text = "취소",
					style = WeskiTheme.typography.title3SemiBold,
					color = WeskiColor.Gray60,
					textAlign = TextAlign.Center
				)

				Text(
					modifier = Modifier
						.weight(1f)
						.background(shape = RoundedCornerShape(8.dp), color = WeskiColor.Main01)
						.noRippleClickable { onConfirm() }
						.padding(vertical = 16.5.dp),
					text = "확인",
					style = WeskiTheme.typography.title3SemiBold,
					color = WeskiColor.White,
					textAlign = TextAlign.Center
				)
			}

		}
	}
}

fun Context.isNotificationGranted(): Boolean {
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
		return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
			this,  // 또는 그냥 this 생략 가능
			android.Manifest.permission.POST_NOTIFICATIONS
		)
	} else {
		val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
		return notificationManager.areNotificationsEnabled()
	}
}
