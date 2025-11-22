package com.dieski.weski

import android.app.Application
import com.dieski.weski.notification.createNotificationChannel
import com.dieski.weski.util.DebugTimberTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class WeskiApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		Timber.plant(DebugTimberTree())
		createNotificationChannel()
	}
}