import com.android.build.api.dsl.ApplicationExtension
import com.dieski.convention.findPluginId
import com.dieski.convention.libs
import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationFirebaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPluginId("google.services"))
                apply(libs.findPluginId("firebase.crashlytics"))
            }

            dependencies {
                add("implementation", libs.findBundle("firebase").get())
                add("implementation", platform(libs.findLibrary("firebase-bom").get()))
            }

            extensions.configure<ApplicationExtension> {
                buildTypes.configureEach {
                    if (name == "release") {
                        configure<CrashlyticsExtension> {
                            mappingFileUploadEnabled = true
                        }
                    } else {
                        configure<CrashlyticsExtension> {
                            mappingFileUploadEnabled = false
                        }
                    }
                }
            }
        }
    }
}
