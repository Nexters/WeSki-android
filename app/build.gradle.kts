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
		versionName = "1.0.2"
		versionCode = 2024_11_29_01
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
        create("benchmark") {
            initWith(buildTypes.getByName("debug"))
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
        release {
			isMinifyEnabled = true
			signingConfig = signingConfigs.getByName("debug")
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}

	buildFeatures {
		buildConfig = true
	}
}

dependencies {
	implementation(projects.presentation)
	implementation(projects.domain)
	implementation(projects.data)
	implementation(projects.remote)
	implementation(projects.analytics)

	implementation(libs.androidx.compose.activity)
	implementation(libs.androidx.appcompat)
	implementation(libs.androidx.core.ktx)
	implementation(libs.material)
	implementation(libs.bundles.androidx.compose.navigation)
	implementation(libs.kotlin.collections.immutable)
	implementation(libs.firebase.analytics)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.androidx.ui.test.junit4)
}