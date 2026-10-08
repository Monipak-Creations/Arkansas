import com.monipakcreations.arkansas.lib
import com.monipakcreations.arkansas.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.google.devtools.ksp")
        dependencies {
            "ksp"(libs.lib("hilt-compiler"))
        }

        // Pure Kotlin/JVM modules only need the Dagger/Hilt core annotations.
        pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
            dependencies {
                "implementation"(libs.lib("hilt-core"))
            }
        }

        // Android modules get the full Hilt Gradle plugin.
        pluginManager.withPlugin("com.android.base") {
            pluginManager.apply("com.google.dagger.hilt.android")
            dependencies {
                "implementation"(libs.lib("hilt-android"))
            }
        }
    }
}
