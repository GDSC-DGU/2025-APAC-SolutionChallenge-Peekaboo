package com.peekaboo.plugin

class KotlinSerializationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(plugins) {
            apply("org.jetbrains.kotlin.plugin.serialization")
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        dependencies {
            "implementation"(libs.findLibrary("kotlin.serialization.json").get())
        }
    }
}