plugins {
	id("weski.android.library")
	id("weski.android.hilt")
}

android {
	namespace = "com.dieski.analytics"

	buildFeatures {
		buildConfig = true
	}
}

dependencies {
	implementation(platform(libs.firebase.bom))
	implementation(libs.firebase.analytics)
	implementation(libs.firebase.crashlytics)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}