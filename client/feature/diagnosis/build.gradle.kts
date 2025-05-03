plugins {
    id("peekaboo.android.feature")
    id("peekaboo.android.compose")
}

android {
    namespace = "com.peekaboo.diagnosis"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.core)
    implementation(projects.core.ui)
    implementation(projects.core.designSystem)
}