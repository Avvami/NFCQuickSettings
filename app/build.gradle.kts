import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

val localProperties: Properties = gradleLocalProperties(rootDir, providers)

android {
    namespace = "ru.ariadna.nfcquicksettings"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "ru.ariadna.nfcquicksettings"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file(localProperties.getProperty("storeFile"))
            storePassword = localProperties.getProperty("storePassword")
            keyAlias = localProperties.getProperty("keyAlias")
            keyPassword = localProperties.getProperty("keyPassword")

            enableV1Signing = true
            enableV2Signing = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as com.android.build.gradle.internal.api.ApkVariantOutputImpl }
            .forEach { output ->
                val buildType = variant.buildType.name
                val outputFileName = if (buildType == "release") {
                    "nfc-quick-settings-v${defaultConfig.versionName}_build_${defaultConfig.versionCode}.apk"
                } else {
                    "nfc-quick-settings-v${defaultConfig.versionName}_build_${defaultConfig.versionCode}-$buildType.apk"
                }
                output.outputFileName = outputFileName
            }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}