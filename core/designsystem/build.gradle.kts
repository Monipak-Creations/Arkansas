plugins {
    alias(libs.plugins.arkansas.library.compose)
}

android {
    namespace = "com.monipakcreations.arkansas.core.designsystem"
}

dependencies {
    implementation(libs.glide.compose)
}
