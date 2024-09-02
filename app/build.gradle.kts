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
            java.srcDir("$buildDir/generated/src/main/kotlin")
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



// Add the OpenAPI Generator configuration here
openApiGenerate {
    inputSpec.set("$rootDir/specs/openapi.yaml")
    generatorName.set("kotlin")
    outputDir.set("$buildDir/generated")
    apiPackage.set("ci.harma.habitrack.api")
    modelPackage.set("ci.harma.habitrack.model")
    invokerPackage.set("ci.harma.habitrack.invoker")
}
