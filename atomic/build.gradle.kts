import org.jetbrains.kotlin.konan.target.HostManager
import pw.binom.publish.plugins.PublicationPomInfoExtension

plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("pw.binom.publish")
}
kotlin {
    if (HostManager.hostIsMac) {
        macosX64()
        macosArm64()
        iosX64()
        iosArm64()
        iosSimulatorArm64()
        tvosArm64()
        tvosSimulatorArm64()
        watchosArm32()
        watchosArm64()
        watchosDeviceArm64()
        watchosSimulatorArm64()
        watchosX64()
    }
    jvm()
    linuxX64()
    linuxArm64()
    mingwX64()
    js {
        browser()
        nodejs()
    }
    wasmJs {
        browser()
        nodejs()
        d8()
    }
    wasmWasi {
        nodejs()
    }
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX64()
    androidNativeX86()

    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlin("stdlib"))
            }
        }

        val commonTest by getting {
            dependencies {
                api(kotlin("test"))
                api(kotlin("test-common"))
                api(kotlin("test-annotations-common"))
            }
        }
        val jvmTest by getting {
//                dependsOn(commonTest)
            dependencies {
//                    api(kotlin("test"))
                implementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
                implementation("org.junit.jupiter:junit-jupiter-engine:5.7.1")
            }
        }
        val fakeImplMain by creating
        val jsMain by getting {
            dependsOn(fakeImplMain)
        }
        val wasmJsMain by getting {
            dependsOn(fakeImplMain)
        }
        val wasmWasiMain by getting {
            dependsOn(fakeImplMain)
        }
        applyDefaultHierarchyTemplate()
    }
}
val publicationPomInfoExtension =
    extensions.getByType(PublicationPomInfoExtension::class.java).apply {
        useApache2License()
        gitScm("https://github.com/caffeine-mgn/atomic")
        author(
            id = "subochev",
            name = "Anton Subochev",
            email = "caffeine.mgn@gmail.com",
        )
    }
