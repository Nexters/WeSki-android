package com.dieski.weski.presentation.core.notification

/**
 *
 * @author   JGeun
 * @created  2025/08/31 
 */
sealed interface NotificationPermissionState {
	data object NONE : NotificationPermissionState
	data object Granted : NotificationPermissionState
	data object Denied : NotificationPermissionState
	data object ShouldShowRationale : NotificationPermissionState
}