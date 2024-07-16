package com.dieski.weski

import android.app.Application
import com.naver.maps.map.NaverMapSdk

class WeskiApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient(BuildConfig.NAVER_MAP_SDK_CLIENTID)
    }
}