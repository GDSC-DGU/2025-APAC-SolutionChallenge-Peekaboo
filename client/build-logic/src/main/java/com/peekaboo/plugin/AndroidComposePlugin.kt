package com.peekaboo.plugin


class AndroidComposePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(plugins) {
            apply("org.jetbrains.kotlin.plugin.compose")
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        extensions.getByType<BaseExtension>().apply {
            buildFeatures.apply {
                compose = true
            }
        }
        extensions.configure<ComposeCompilerGradlePluginExtension> {
            featureFlags.set(setOf(ComposeFeatureFlag.StrongSkipping))
            includeSourceInformation.set(true)
        }
        dependencies {
            "implementation"(platform(libs.findLibrary("androidx.compose.bom").get()))
            "implementation"(libs.findBundle("compose").get())
            "implementation"(libs.findLibrary("coil-compose").get())
            "implementation"(libs.findLibrary("androidx.navigation.compose").get())
        }
    }
}