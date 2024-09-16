plugins {
	id("weski.android.library")
	id("weski.android.hilt")
	id("weski.android.library.compose")
}

android {
	namespace = "com.dieski.weski.presentation"
}

dependencies {
	implementation(projects.domain)
	implementation(projects.data)
	implementation(projects.analytics)

	implementation(libs.material)
	implementation(libs.dagger.hilt.android)
	implementation(libs.bundles.androidx.compose.navigation)
	implementation(libs.kotlin.collections.immutable)
	debugImplementation(libs.bundles.androidx.compose.debug)
	implementation(libs.coil.compose)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}