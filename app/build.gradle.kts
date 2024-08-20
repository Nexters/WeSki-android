import java.io.FileInputStream
import java.util.Properties

plugins {
	id("weski.android.application")
	id("weski.android.hilt")
	id("weski.android.application.compose")
	id("weski.android.application.firebase")
}

val properties = Properties().apply {
	load(FileInputStream(rootProject.file("local.properties")))
}

android {
	namespace = "com.dieski.weski"

	defaultConfig {
		applicationId = "com.dieski.weski"
		versionName = "0.0.1"
		versionCode = 2024_08_22_02
	}

	signingConfigs {
		create("release") {
		}
	}

	buildTypes {
		debug {
			isMinifyEnabled = false
			isDebuggable = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-project.txt"
			)
		}
		release {
			isMinifyEnabled = true
			signingConfig = signingConfigs.getByName("debug")
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
}

dependencies {
	implementation(projects.presentation)
	implementation(projects.domain)
	implementation(projects.data)

	implementation(libs.androidx.compose.activity)
	implementation(libs.androidx.appcompat)
	implementation(libs.androidx.core.ktx)
	implementation(libs.material)
	implementation(libs.bundles.androidx.compose.navigation)
	implementation(libs.kotlin.collections.immutable)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.androidx.ui.test.junit4)
}