// Top-level build file where you can add configuration options common to all sub-projects/modules.

//dependency to included buildscript NKHL_2
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.0.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.8.5")

    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}