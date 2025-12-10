// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // No need to add Realm here if using the Gradle Plugin DSL in module
    }
}

plugins {
    // Optional: keep your plugin aliases
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    id("io.realm.kotlin") version "3.0.0" apply false // Realm Kotlin plugin
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
