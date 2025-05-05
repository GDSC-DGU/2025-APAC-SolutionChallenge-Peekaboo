plugins {
    id("peekaboo.android.feature")
    id("peekaboo.android.compose")
}

android {
    namespace = "com.peekaboo.multipart"
}

dependencies {
    implementation(projects.core)
}