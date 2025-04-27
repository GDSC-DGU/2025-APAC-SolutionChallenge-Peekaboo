plugins {
    id("peekaboo.android.application")
    id("peekaboo.android.hilt")
    id("peekaboo.android.kotlin")
    id("peekaboo.retrofit")
}

android {
    namespace = "com.peekaboo.baebae"

    defaultConfig {
    }
}

dependencies {
    implementation(projects.feature)
    implementation(projects.domain)
    implementation(projects.core)
    implementation(projects.data)
}