plugins {
    id("peekaboo.android.feature")
    id("peekaboo.android.compose")
    id("peekaboo.android.hilt")
    id("peekaboo.android.kotlin")
}

android {
    namespace = "com.peekaboo.feature"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.core)
}