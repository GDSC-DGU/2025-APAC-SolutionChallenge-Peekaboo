plugins {
    id("peekaboo.android.feature")
    id("peekaboo.android.compose")
}

android {
    namespace = "com.peekaboo.navigation"
}


dependencies {
    implementation(projects.core)
}