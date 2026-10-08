plugins {
    alias(libs.plugins.arkansas.android.feature)
}

android {
    namespace = "com.monipakcreations.arkansas.feature.products"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.database)
}
