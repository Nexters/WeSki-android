import com.android.build.gradle.LibraryExtension
import com.dieski.convention.configureKotlinAndroid
import com.dieski.convention.findPluginId
import com.dieski.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPluginId("android.library"))
                apply(libs.findPluginId("kotlin.android"))
                apply(libs.findPluginId("kotlin.serialization"))
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }
        }
    }
}
