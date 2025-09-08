package com.dieski.weski.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.dieski.weski.R
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import com.dieski.weski.presentation.R as presentationR

/**
 *
 * @author   JGeun
 * @created  2025/09/08
 */
fun Context.createNotificationChannel() {
	val notificationManager = applicationContext.getSystemService(NOTIFICATION_SERVICE) as? NotificationManager

	val channelId = applicationContext.getString(R.string.channel_id)
	val channelName = applicationContext.getString(R.string.channel_name)

	val channel = NotificationChannel(
		channelId,
		channelName,
		NotificationManager.IMPORTANCE_HIGH,
	)
	notificationManager?.createNotificationChannel(channel)
}

fun Context.defaultNotification(
	pendingIntent: PendingIntent? = null,
	channelId: String,
): NotificationCompat.Builder = NotificationCompat.Builder(
	this,
	channelId,
)
	.setContentIntent(pendingIntent)
	.setSmallIcon(presentationR.drawable.ic_weski_logo)
	.setPriority(NotificationCompat.PRIORITY_HIGH)
	.setColorized(true)
	.setAutoCancel(true)
	.setOnlyAlertOnce(true)
	.setCategory(NotificationCompat.CATEGORY_MESSAGE)
	.setGroup(getString(R.string.channel_group_name))

fun Context.summaryNotification(
	pendingIntent: PendingIntent? = null,
	channelId: String,
): NotificationCompat.Builder = this.defaultNotification(pendingIntent, channelId)
	.setGroupSummary(true)

fun getTriggerTimeInMillis(time: LocalTime): Long {
	val now = LocalDate.now()
	val dateTime = time.atDate(now)
	return dateTime
		.atZone(ZoneId.systemDefault())
		.toInstant()
		.toEpochMilli()
}

fun Context.deleteNotificationChannelIfExists(channelId: String) {
	val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

	val existingChannel = notificationManager.getNotificationChannel(channelId)
	if (existingChannel != null) {
		notificationManager.deleteNotificationChannel(channelId)
	}
}

fun Context.createInterruptNotificationChannel() {
	val notificationManager =
		applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

	val channelId = applicationContext.getString(R.string.interrupt_channel_id)
	val channelName = applicationContext.getString(R.string.interrupt_channel_name)

	val channel =
		NotificationChannel(
			channelId,
			channelName,
			NotificationManager.IMPORTANCE_HIGH,
		).apply {
			setSound(null, null)
		}
	notificationManager.createNotificationChannel(channel)
}

fun Context.isNotificationGranted(context: Context): Boolean {
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
		return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS)
	} else {
		val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
		return notificationManager.areNotificationsEnabled()
	}
}