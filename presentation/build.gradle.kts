plugins {
	id("weski.android.library")
	id("weski.android.hilt")
	id("weski.android.library.compose")
}

android {
	namespace = "com.dieski.weski.presentation"
}

dependencies {

	implementation(libs.material)
	implementation(libs.dagger.hilt.android)
	implementation(libs.bundles.androidx.compose.navigation)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}