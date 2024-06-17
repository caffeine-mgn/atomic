import pw.binom.publish.allTargets
import pw.binom.publish.applyDefaultHierarchyBinomTemplate
import pw.binom.publish.plugins.PublicationPomInfoExtension

plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("pw.binom.publish")
}
kotlin {
    allTargets()
    applyDefaultHierarchyBinomTemplate()

    targets.all {
        compilations.all {
            compilerOptions.configure {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }

    sourceSets {
        commonTest.dependencies {
            api(kotlin("test"))
            api(kotlin("test-common"))
            api(kotlin("test-annotations-common"))
        }

        jvmTest.dependencies {
//                    api(kotlin("test"))
            implementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
            implementation("org.junit.jupiter:junit-jupiter-engine:5.7.1")
        }
        val fakeImplMain by creating {
            dependsOn(commonMain.get())
        }
        jsMain {
            dependsOn(fakeImplMain)
        }
        val jsMain by getting {
            dependsOn(fakeImplMain)
        }
        this.findByName("wasmJsMain")?.dependsOn(fakeImplMain)
        this.findByName("wasmWasiMain")?.dependsOn(fakeImplMain)
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
