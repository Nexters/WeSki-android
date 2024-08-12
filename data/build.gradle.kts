plugins {
	id("weski.android.library")
	id("weski.android.hilt")
}

android {
	namespace = "com.dieski.data"
}

dependencies {
	implementation(project(":domain"))

	implementation(libs.kotlin.serialization.json)
	implementation(libs.kotlin.serialization.converter)
	implementation(libs.retrofit)
	implementation(libs.okhttp)
	implementation(libs.okhttp.logging)
	implementation(libs.kotlin.coroutine.core)
	implementation(libs.androidx.datastore)
	implementation(libs.androidx.room.runtime)
	implementation(libs.androidx.room.ktx)
	ksp(libs.androidx.room.compiler)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}