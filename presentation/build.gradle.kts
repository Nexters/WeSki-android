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

	implementation(libs.material)
	implementation(libs.dagger.hilt.android)
	implementation(libs.bundles.androidx.compose.navigation)
	implementation(libs.kotlin.collections.immutable)
	implementation("io.coil-kt:coil-compose:2.7.0")

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}