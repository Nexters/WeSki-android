plugins {
	`kotlin-dsl`
}

group = "com.dieski.buildlogic"

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
	compileOnly(libs.android.gradlePlugin)
	compileOnly(libs.kotlin.gradlePlugin)
	compileOnly(libs.ksp.gradlePlugin)
	compileOnly(libs.compose.compiler.extension)
	compileOnly(libs.firebase.crashlytics.gradle)
}

gradlePlugin {
	plugins {
		register("androidApplication") {
			id = "weski.android.application"
			implementationClass = "AndroidApplicationConventionPlugin"
		}
		register("androidApplicationCompose") {
			id = "weski.android.application.compose"
			implementationClass = "AndroidApplicationComposeConventionPlugin"
		}
		register("androidFirebase") {
			id = "weski.android.application.firebase"
			implementationClass = "AndroidApplicationFirebaseConventionPlugin"
		}
		register("androidLibrary") {
			id = "weski.android.library"
			implementationClass = "AndroidLibraryConventionPlugin"
		}
		register("androidLibraryCompose") {
			id = "weski.android.library.compose"
			implementationClass = "AndroidLibraryComposeConventionPlugin"
		}
		register("androidHilt") {
			id = "weski.android.hilt"
			implementationClass = "AndroidHiltConventionPlugin"
		}
	}
}