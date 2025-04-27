plugins {
    id("peekaboo.android.feature")
    id("peekaboo.android.compose")
}

android {
    namespace = "com.peekaboo.design_system"
}

dependencies {
    implementation(projects.core)
}