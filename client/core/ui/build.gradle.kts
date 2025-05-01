plugins {
    id("peekaboo.android.feature")
    id("peekaboo.android.compose")
}

android {
    namespace = "com.peekaboo.ui"
}

dependencies {
    implementation(projects.core)
    implementation(projects.core.designSystem)
    implementation(projects.domain)
}