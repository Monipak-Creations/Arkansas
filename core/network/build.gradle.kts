plugins {
    alias(libs.plugins.arkansas.android.library)
    alias(libs.plugins.arkansas.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.monipakcreations.arkansas.core.network"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://dummyjson.com/\"")
    }
}

dependencies {
    api(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)
    api(libs.kotlinx.serialization.json)
}
