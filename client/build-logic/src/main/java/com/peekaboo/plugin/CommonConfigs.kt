package com.peekaboo.plugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.util.Properties

internal fun Project.configureAndroidCommonPlugin() {
    val properties = Properties().apply {
        load(rootProject.file("local.properties").inputStream())
    }

    apply<AndroidKotlinPlugin>()
    apply<KotlinSerializationPlugin>()
    apply<RetrofitPlugin>()
    apply<AndroidHiltPlugin>()

    with(plugins) {
        apply("kotlin-parcelize")
    }

    extensions.getByType<BaseExtension>().apply {
        defaultConfig {
            //
        }

        buildFeatures.apply {
            viewBinding = true
            buildConfig = true
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    dependencies {
        "implementation"(libs.findLibrary("core.ktx").get())
        "implementation"(libs.findLibrary("androidx.appcompat").get())
        "implementation"(libs.findLibrary("lifecycle.viewmodel").get())
        "implementation"(libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
        "implementation"(libs.findLibrary("activity").get())
        "implementation"(libs.findLibrary("material").get())
        "implementation"(libs.findLibrary("timber").get())
    }
}