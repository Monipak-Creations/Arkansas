plugins {
    alias(libs.plugins.arkansas.android.library)
    alias(libs.plugins.arkansas.hilt)
    alias(libs.plugins.room)
}

android {
    namespace = "com.monipakcreations.arkansas.core.database"
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    api(libs.room.runtime)
    api(libs.room.ktx)
    ksp(libs.room.compiler)
}
