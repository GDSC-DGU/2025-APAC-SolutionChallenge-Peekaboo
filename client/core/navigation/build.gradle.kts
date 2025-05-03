plugins {
    id("peekaboo.android.feature")
    id("peekaboo.android.compose")
}

android {
    namespace = "com.peekaboo.navigation"
}


dependencies {
    implementation(projects.core)
    implementation(projects.domain)

    implementation(projects.feature.onboarding)
    implementation(projects.feature.home)
    implementation(projects.feature.diagnosis)
    implementation(projects.feature.diagnosishistory)
    implementation(projects.feature.diagnosisquick)
}