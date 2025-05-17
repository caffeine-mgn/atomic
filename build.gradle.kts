import pw.binom.publish.getExternalVersion

plugins {
    id("com.github.gmazzo.buildconfig") version "3.0.3" apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
//    kotlin("multiplatform") version "2.0.20" apply false
    alias(libs.plugins.binom.publish) apply false
}

allprojects {
    version = getExternalVersion()
    group = "pw.binom"

    repositories {
        mavenLocal()
        maven(url = "https://repo.binom.pw")
        mavenCentral()
        gradlePluginPortal()
    }
}

tasks {
    val publishToMavenLocal by creating {
        val self = this
        getTasksByName("publishToMavenLocal", true).forEach {
            if (it !== self) {
                dependsOn(it)
            }
        }
    }

    val publish by creating {
        val self = this
        getTasksByName("publish", true).forEach {
            if (it !== self) {
                dependsOn(it)
            }
        }
    }
}
