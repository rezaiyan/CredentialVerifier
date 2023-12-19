pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    val kotlinVersion = "1.9.21"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
    kotlin("plugin.serialization") version kotlinVersion apply false
}

rootProject.name = "codeChallange"