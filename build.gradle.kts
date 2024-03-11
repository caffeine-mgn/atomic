import pw.binom.publish.getExternalVersion

buildscript {

  repositories {
    mavenLocal()
    maven(url = "https://repo.binom.pw")
    mavenCentral()
    gradlePluginPortal()
  }
  dependencies {
//    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${pw.binom.Versions.KOTLIN_VERSION}")
//    classpath("org.jetbrains.kotlin:kotlin-serialization:${pw.binom.Versions.KOTLIN_VERSION}")
//    classpath("com.bmuschko:gradle-docker-plugin:6.6.1")
  }
}

plugins {
  id("com.github.gmazzo.buildconfig") version "3.0.3" apply false
  id("pw.binom.publish") version "0.1.14" apply false
  kotlin("multiplatform") version "1.9.23"  apply false
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
