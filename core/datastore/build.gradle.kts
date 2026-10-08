plugins {
    alias(libs.plugins.arkansas.android.library)
    alias(libs.plugins.arkansas.hilt)
}

android {
    namespace = "com.monipakcreations.arkansas.core.datastore"
}

dependencies {
    api(libs.androidx.datastore.preferences)
}
