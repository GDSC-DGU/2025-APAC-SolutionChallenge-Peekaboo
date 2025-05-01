plugins {
    `kotlin-dsl`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.agp)
    implementation(libs.kotlin.gradleplugin)
    compileOnly(libs.compose.compiler.extension)
}

gradlePlugin {
    plugins {
        register("android-application") {
            id = "peekaboo.android.application"
            implementationClass = "com.peekaboo.plugin.AndroidApplicationPlugin"
        }
        register("android-compose") {
            id = "peekaboo.android.compose"
            implementationClass = "com.peekaboo.plugin.AndroidComposePlugin"
        }
        register("android-feature") {
            id = "peekaboo.android.feature"
            implementationClass = "com.peekaboo.plugin.AndroidFeaturePlugin"
        }
        register("android-hilt") {
            id = "peekaboo.android.hilt"
            implementationClass = "com.peekaboo.plugin.AndroidHiltPlugin"
        }
        register("android-kotlin") {
            id = "peekaboo.android.kotlin"
            implementationClass = "com.peekaboo.plugin.AndroidKotlinPlugin"
        }
        register("kotlin-jvm") {
            id = "peekaboo.kotlin.jvm"
            implementationClass = "com.peekaboo.plugin.KotlinJvmPlugin"
        }
        register("kotlin-serialization") {
            id = "peekaboo.kotlin.serialization"
            implementationClass = "com.peekaboo.plugin.KotlinSerializationPlugin"
        }
        register("retrofit") {
            id = "peekaboo.retrofit"
            implementationClass = "com.peekaboo.plugin.RetrofitPlugin"
        }
    }
}