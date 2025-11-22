import java.io.FileInputStream
import java.util.Properties

plugins {
	id("weski.android.library")
	id("weski.android.hilt")
	id("weski.android.library.compose")
}

val properties = Properties().apply {
	load(FileInputStream(rootProject.file("local.properties")))
}

android {
	namespace = "com.dieski.weski.presentation"

	defaultConfig {
		buildConfigField("String", "BASE_URL", properties["BASE_URL"].toString())

		buildConfigField("String", "DETAIL_SLOPE_BANNER1_AD_UNIT_ID", properties["DETAIL_SLOPE_BANNER1_AD_UNIT_ID"].toString())
		buildConfigField("String", "DETAIL_SLOPE_BOTTOM_BANNER_AD_UNIT_ID", properties["DETAIL_SLOPE_BOTTOM_BANNER_AD_UNIT_ID"].toString())
		buildConfigField("String", "DETAIL_WEATHER_BANNER1_AD_UNIT_ID", properties["DETAIL_WEATHER_BANNER1_AD_UNIT_ID"].toString())
		buildConfigField("String", "DETAIL_WEATHER_BOTTOM_BANNER_AD_UNIT_ID", properties["DETAIL_WEATHER_BOTTOM_BANNER_AD_UNIT_ID"].toString())
		buildConfigField("String", "DETAIL_WEBCAM_BANNER1_AD_UNIT_ID", properties["DETAIL_WEBCAM_BANNER1_AD_UNIT_ID"].toString())
		buildConfigField("String", "DETAIL_WEBCAM_BOTTOM_BANNER_AD_UNIT_ID", properties["DETAIL_WEBCAM_BOTTOM_BANNER_AD_UNIT_ID"].toString())
		buildConfigField("String", "HOME_BOTTOM_BANNER_AD_UNIT_ID", properties["HOME_BOTTOM_BANNER_AD_UNIT_ID"].toString())
		buildConfigField("String", "HOME_FAVORITES_BANNER_AD_UNIT_ID", properties["HOME_FAVORITES_BANNER_AD_UNIT_ID"].toString())
		buildConfigField("String", "HOME_NATIVE_BANNER1_AD_UNIT_ID", properties["HOME_NATIVE_BANNER1_AD_UNIT_ID"].toString())
	}
}

dependencies {
	implementation(projects.domain)
	implementation(projects.data)
	implementation(projects.analytics)

	implementation(libs.material)
	implementation(libs.dagger.hilt.android)
	implementation(libs.bundles.androidx.compose.navigation)
	implementation(libs.kotlin.collections.immutable)
	implementation(libs.androidx.compose.ui.tooling)
	debugImplementation(libs.bundles.androidx.compose.debug)
	implementation(libs.androidx.media3.exoplayer)
	implementation(libs.coil.compose)
	implementation(libs.play.services.ads)
	implementation("androidx.activity:activity-ktx:1.2.2")
	implementation("androidx.fragment:fragment-ktx:1.3.2")

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	debugImplementation(libs.androidx.compose.ui.tooling)

	implementation(libs.androidx.media3.ui)
	implementation(libs.androidx.media3.exoplayer.hls)
}