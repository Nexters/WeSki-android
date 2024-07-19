import com.android.build.api.dsl.ApplicationExtension
import com.dieski.convention.ProjectConfigurations
import com.dieski.convention.configureKotlinAndroid
import com.dieski.convention.findPluginId
import com.dieski.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPluginId("android.application"))
                apply(libs.findPluginId("kotlin.android"))
                apply(libs.findPluginId("kotlin.serialization"))
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = ProjectConfigurations.TARGET_SDK

                packaging {
                    resources {
                        excludes.add("/META-INF/{AL2.0,LGPL2.1}")
                    }
                }
            }
        }
    }
}
