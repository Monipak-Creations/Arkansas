import com.monipakcreations.arkansas.configureKotlinJvm
import com.monipakcreations.arkansas.lib
import com.monipakcreations.arkansas.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.jvm")
        configureKotlinJvm()
        dependencies {
            "testImplementation"(libs.lib("junit"))
        }
    }
}
