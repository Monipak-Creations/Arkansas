import com.monipakcreations.arkansas.lib
import com.monipakcreations.arkansas.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

/** A feature = Compose UI + ViewModel (Hilt) + Navigation 3 key, depending on domain only. */
class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("arkansas.android.library.compose")
        pluginManager.apply("arkansas.hilt")
        pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

        dependencies {
            "implementation"(project(":core:designsystem"))

            "implementation"(libs.lib("androidx-hilt-lifecycle-viewmodel-compose"))
            "implementation"(libs.lib("androidx-lifecycle-runtime-compose"))
            "implementation"(libs.lib("androidx-lifecycle-viewmodel-compose"))
            "implementation"(libs.lib("androidx-navigation3-runtime"))
            "implementation"(libs.lib("kotlinx-serialization-json"))

            "implementation"(libs.lib("kotlinx-coroutines-core"))
            "testImplementation"(libs.lib("junit"))
            "testImplementation"(libs.lib("kotlinx-coroutines-test"))
        }
    }
}
