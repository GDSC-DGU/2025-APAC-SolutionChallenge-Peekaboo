plugins {
    id("peekaboo.android.feature")
    id("peekaboo.android.compose")
}

android {
    namespace = "com.peekaboo.login"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.core)
    implementation(projects.core.ui)
    implementation(projects.core.designSystem)

    implementation(libs.google.play.services.auth)
    implementation(libs.google.credentail.service)
    implementation(libs.google.credential)
    implementation(libs.google.identity)
}