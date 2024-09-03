plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.openapi.generator") version "7.8.0"
}

android {
    namespace = "ci.harma.habitrack"
    compileSdk = 34

    defaultConfig {
        applicationId = "ci.harma.habitrack"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    sourceSets {
        getByName("main") {
            val buildDirectory = layout.buildDirectory.get().asFile
            java.srcDir("$buildDirectory/generated/src/main/kotlin")
            java.srcDir("$buildDirectory/generated/src/main/java")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}


val apiSpec = "$rootDir/specs/openapi.yaml"

// OpenAPI generator configuration
openApiGenerate {
    inputSpec = apiSpec
//     TODO experiment with generatorName "android" @see: https://openapi-generator.tech/docs/generators/android/
//    generatorName = "android"
    generatorName = "kotlin"
    outputDir = "${layout.buildDirectory.get().asFile}/generated"
    val targetPath = "org.openapitools.client"
    apiPackage = "${targetPath}.api"
    modelPackage = "${targetPath}.model"
    invokerPackage = "${targetPath}.invoker"
}

openApiValidate {
    inputSpec = apiSpec
    recommend = true
}
