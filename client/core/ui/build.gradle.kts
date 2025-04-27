plugins {
    id("peekaboo.android.feature")
    id("peekaboo.android.compose")
}

android {
    namespace = "com.peekaboo.ui"
}

dependencies {
    implementation(projects.core)
}