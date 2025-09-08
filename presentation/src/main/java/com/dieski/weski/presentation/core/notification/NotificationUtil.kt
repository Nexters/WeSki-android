package com.dieski.weski.presentation.core.notification

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 *
 * @author   JGeun
 * @created  2025/08/31
 */
internal fun hasPermission(context: Context): Boolean =
	if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) true
	else ContextCompat.checkSelfPermission(
		context, Manifest.permission.POST_NOTIFICATIONS
	) == PackageManager.PERMISSION_GRANTED

fun requestInternalNotificationPermission(
	activity: Activity,
	launcher: ManagedActivityResultLauncher<String, Boolean>,
	onPermissionResult: (NotificationPermissionState) -> Unit
) {
	if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
		onPermissionResult(NotificationPermissionState.Granted)
		return
	}
	when {
		hasPermission(activity) -> onPermissionResult(NotificationPermissionState.Granted)
		ActivityCompat.shouldShowRequestPermissionRationale(
			activity, Manifest.permission.POST_NOTIFICATIONS
		) -> {
			// 간단 라쇼날: 바로 요청 전에 설명
			launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
		}

		else -> launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
	}
}
