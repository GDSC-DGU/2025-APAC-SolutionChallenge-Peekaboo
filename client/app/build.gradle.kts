import java.util.Properties

plugins {
    id("peekaboo.android.application")
    id("peekaboo.android.hilt")
    id("peekaboo.android.kotlin")
    id("peekaboo.retrofit")
}

val properties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.peekaboo.baebae"

    defaultConfig {
        val googleLogInKey = properties.getProperty("GOOGLE_CLIENT_ID")
        buildConfigField("String", "GOOGLE_CLIENT_ID", "\"$googleLogInKey\"")
    }
}

dependencies {
    implementation(projects.feature)
    implementation(projects.domain)
    implementation(projects.core)
    implementation(projects.data)

    implementation(libs.gson)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.okhttp.urlconnection)

    implementation(libs.google.play.services.auth)
    implementation(libs.google.credentail.service)
    implementation(libs.google.credential)
    implementation(libs.google.identity)
}