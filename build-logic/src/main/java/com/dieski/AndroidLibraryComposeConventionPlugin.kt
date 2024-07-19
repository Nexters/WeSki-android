import com.android.build.gradle.LibraryExtension
import com.dieski.convention.configureAndroidCompose
import com.dieski.convention.findPluginId
import com.dieski.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPluginId("compose.compiler"))
                apply(libs.findPluginId("android.library"))
            }

            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
            }
        }
    }
}
