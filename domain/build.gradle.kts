import org.gradle.kotlin.dsl.implementation

plugins {
    id("java-library")
    kotlin("plugin.serialization") version "1.9.20"
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    //Serializer
    implementation(libs.kotlinx.serialization)
}
