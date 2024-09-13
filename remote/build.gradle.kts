import java.io.FileInputStream
import java.util.Properties

plugins {
	id("weski.android.library")
	id("weski.android.hilt")
}

val properties = Properties().apply {
	load(FileInputStream(rootProject.file("local.properties")))
}

android {
	namespace = "com.dieski.remote"

	defaultConfig {
		buildConfigField("String", "BASE_URL", properties["BASE_URL"].toString())
	}

	buildFeatures {
		buildConfig = true
	}
}

dependencies {
	implementation(projects.domain)

	implementation(libs.kotlin.serialization.json)
	implementation(libs.kotlin.serialization.converter)
	implementation(libs.retrofit)
	implementation(libs.okhttp)
	implementation(libs.okhttp.logging)
	implementation(libs.kotlin.coroutine.core)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}