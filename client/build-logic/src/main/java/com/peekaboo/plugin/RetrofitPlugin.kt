package com.peekaboo.plugin

class RetrofitPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        dependencies {
            "implementation"(platform(libs.findLibrary("retrofit-bom").get()))
            "implementation"(libs.findLibrary("retrofit").get())
            "implementation"(libs.findLibrary("retrofit-kotlin-serialization-converter").get())
            "implementation"(libs.findLibrary("retrofit-response-type-keeper").get())
        }
    }
}