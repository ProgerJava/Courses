plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.olejnikov.testovoe.courses"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.olejnikov.testovoe.courses"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.$versionName"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    //DI
    implementation(libs.koin.android)
    implementation(libs.koin.core)
    //Gson
    implementation(libs.converter.gson)

}