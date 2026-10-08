plugins {
    alias(libs.plugins.arkansas.android.feature)
}

android {
    namespace = "com.monipakcreations.arkansas.feature.auth"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.database)
    implementation(projects.core.datastore)
}
