import java.io.FileInputStream
import java.util.Properties

plugins {
	id("weski.android.application")
	id("weski.android.hilt")
	id("weski.android.application.compose")
	id("weski.android.application.firebase")
	alias(libs.plugins.google.services)
}

android {
	namespace = "com.dieski.weski"

	defaultConfig {
		applicationId = "com.dieski.weski"
		versionName = "3.0.2"
		versionCode = 2025_04_06_02
	}

	signingConfigs {
		create("release") {
		}
	}

	buildTypes {
		debug {
			val debugProperties = Properties().apply {
				val debugPropertiesFile = rootProject.file("local.debug.properties")
				if (debugPropertiesFile.exists()) {
					load(FileInputStream(debugPropertiesFile))
				} else {
					load(FileInputStream(rootProject.file("local.properties")))
				}
			}
			isMinifyEnabled = false
			isDebuggable = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-project.txt"
			)
			manifestPlaceholders["ADMOB_APP_ID"] = debugProperties["ADMOB_APP_ID"] as Any
		}
        create("benchmark") {
			val debugProperties = Properties().apply {
				val debugPropertiesFile = rootProject.file("local.debug.properties")
				if (debugPropertiesFile.exists()) {
					load(FileInputStream(debugPropertiesFile))
				} else {
					load(FileInputStream(rootProject.file("local.properties")))
				}
			}
            initWith(buildTypes.getByName("debug"))
            matchingFallbacks += listOf("release")
            isDebuggable = false
			manifestPlaceholders["ADMOB_APP_ID"] = debugProperties["ADMOB_APP_ID"] as Any
        }
        release {
			val releaseProperties = Properties().apply {
				val releasePropertiesFile = rootProject.file("local.release.properties")
				if (releasePropertiesFile.exists()) {
					load(FileInputStream(releasePropertiesFile))
				} else {
					load(FileInputStream(rootProject.file("local.properties")))
				}
			}
			isDebuggable = false
			isMinifyEnabled = true
			signingConfig = signingConfigs.getByName("debug")
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
			manifestPlaceholders["ADMOB_APP_ID"] = releaseProperties["ADMOB_APP_ID"] as Any
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
	implementation(projects.local)
	implementation(projects.analytics)

	implementation(libs.androidx.compose.activity)
	implementation(libs.androidx.appcompat)
	implementation(libs.androidx.core.ktx)
	implementation(libs.material)
	implementation(libs.bundles.androidx.compose.navigation)
	implementation(libs.kotlin.collections.immutable)
	implementation(libs.firebase.analytics)
	implementation(libs.play.services.ads)

//	implementation(libs.firebase.messaging.ktx)
	implementation(libs.firebase.messaging)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.androidx.ui.test.junit4)
}