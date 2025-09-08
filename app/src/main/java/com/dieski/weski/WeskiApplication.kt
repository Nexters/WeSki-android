package com.dieski.weski

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.dieski.weski.util.DebugTimberTree
//import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.Arrays

@HiltAndroidApp
class WeskiApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		Timber.plant(DebugTimberTree())
	}
}