import org.jetbrains.kotlin.konan.target.HostManager

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
    } else {
        jvm()
        linuxX64()
        linuxArm64()
        mingwX64()
        js()
        wasmJs()
        wasmWasi()
        androidNativeArm32()
        androidNativeArm64()
        androidNativeX64()
        androidNativeX86()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlin("stdlib-common"))
            }
        }

        val commonTest by getting {
            dependencies {
                api(kotlin("test-common"))
                api(kotlin("test-annotations-common"))
//        api("org.jetbrains.kotlinx:kotlinx-coroutines-test:${pw.binom.Versions.KOTLINX_COROUTINES_VERSION}")
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
    }
}
// if (pw.binom.Target.ANDROID_JVM_SUPPORT) {
//  apply<pw.binom.plugins.AndroidSupportPlugin>()
// }
// apply<pw.binom.plugins.ConfigPublishPlugin>()
