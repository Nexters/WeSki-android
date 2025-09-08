import java.io.FileInputStream
import java.util.Properties

plugins {
	id("weski.android.library")
	id("weski.android.hilt")
}

android {
	namespace = "com.dieski.remote"

	buildTypes {
		debug {
			val debugProperties = Properties().apply {
				val debugPropertiesFile = rootProject.file("local.debug.properties")
				if (debugPropertiesFile.exists()) {
					load(FileInputStream(debugPropertiesFile))
				} else {
					// Fallback to default local.properties
					load(FileInputStream(rootProject.file("local.properties")))
				}
			}
			buildConfigField("String", "BASE_URL", debugProperties["BASE_URL"].toString())
		}

		create("benchmark") {
			initWith(buildTypes.getByName("debug"))
			val debugProperties = Properties().apply {
				val debugPropertiesFile = rootProject.file("local.debug.properties")
				if (debugPropertiesFile.exists()) {
					load(FileInputStream(debugPropertiesFile))
				} else {
					load(FileInputStream(rootProject.file("local.properties")))
				}
			}
			buildConfigField("String", "BASE_URL", debugProperties["BASE_URL"].toString())
		}

		release {
			val releaseProperties = Properties().apply {
				val releasePropertiesFile = rootProject.file("local.release.properties")
				if (releasePropertiesFile.exists()) {
					load(FileInputStream(releasePropertiesFile))
				} else {
					// Fallback to default local.properties
					load(FileInputStream(rootProject.file("local.properties")))
				}
			}
			buildConfigField("String", "BASE_URL", releaseProperties["BASE_URL"].toString())
		}
	}

	buildFeatures {
		buildConfig = true
	}
}

dependencies {
	implementation(projects.domain)
	implementation(projects.data)
	implementation(projects.analytics)

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