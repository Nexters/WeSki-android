plugins {
	id("weski.android.library")
	id("weski.android.hilt")
}


android {
	namespace = "com.dieski.local"

	buildFeatures {
		buildConfig = true
	}
}

dependencies {
	implementation(projects.domain)

	implementation(libs.androidx.datastore)
	implementation(libs.androidx.room.ktx)
	implementation(libs.androidx.room.runtime)
	ksp(libs.androidx.room.compiler)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}