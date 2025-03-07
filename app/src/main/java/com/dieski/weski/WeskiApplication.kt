package com.dieski.weski

import android.app.Application
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
//		MobileAds.initialize(this)
//		val testDeviceIds = Arrays.asList("33BE2250B43518CCDA7DE426D04EE231")
//		val configuration = RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
//		MobileAds.setRequestConfiguration(configuration)
	}
}