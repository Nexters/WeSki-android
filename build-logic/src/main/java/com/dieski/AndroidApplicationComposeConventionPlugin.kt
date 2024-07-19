import com.android.build.api.dsl.ApplicationExtension
import com.dieski.convention.configureAndroidCompose
import com.dieski.convention.findPluginId
import com.dieski.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPluginId("android.application"))
                apply(libs.findPluginId("compose.compiler"))
            }

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
        }
    }
}
