plugins {
	id("weski.android.library")
	id("weski.android.hilt")
}

android {
	namespace = "com.dieski.domain"
}

dependencies {
	implementation(libs.kotlin.coroutine.core)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}