plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.olejnikov.testovoe.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField ("String", "API_BASE_URL", "\"https://drive.usercontent.google.com\"")
        }
        debug {
            buildConfigField ("String", "API_BASE_URL", "\"https://drive.usercontent.google.com\"")
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
    implementation(project(":domain"))
    implementation (libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    //Gson
    implementation(libs.converter.gson)
    //DI
    implementation(libs.koin.android)
    implementation(libs.koin.core)
    //Network
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp3)


}