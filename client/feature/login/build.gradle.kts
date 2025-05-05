import java.util.Properties

plugins {
    id("peekaboo.android.feature")
    id("peekaboo.android.compose")
}

val properties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.peekaboo.login"

    defaultConfig {
        val googleLogInKey = properties.getProperty("GOOGLE_CLIENT_ID")
        buildConfigField("String", "GOOGLE_CLIENT_ID", "\"$googleLogInKey\"")
    }
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