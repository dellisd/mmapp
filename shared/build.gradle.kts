import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

repositories {
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
    mavenLocal()
}

android {
    compileSdkVersion(29)

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        getByName("main") {
            java.srcDirs("src/androidMain/kotlin")
            res.srcDirs("src/androidMain/res")
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
        }
        getByName("test") {
            java.srcDirs("src/androidTest/kotlin")
            res.srcDirs("src/androidTest/res")
            manifest.srcFile("src/androidTest/AndroidManifest.xml")
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    packagingOptions {
        exclude("META-INF/kotlinx-serialization-runtime.kotlin_module")
    }
}

kotlin {
    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "Shared"
            }
        }
        compilations.getByName("main") {
            cinterops.create("Mapbox") {
                defFile(project.file("src/iosMain/defs/Mapbox.def"))

                val frameworks = listOf(project.file("../ios/Pods/Mapbox-iOS-SDK/dynamic"))
                val frameworkOpts = frameworks.map { "-F${it.path}" }
                compilerOpts(*frameworkOpts.toTypedArray())
            }
        }
    }

    android()

    js {
        useCommonJs()
        browser()

        binaries.executable()
    }

    sourceSets["commonMain"].dependencies {
        implementation("io.github.dellisd.spatialk:geojson:0.0.2-SNAPSHOT")
        implementation("io.github.dellisd.spatialk:geojson-dsl:0.0.2-SNAPSHOT")
    }

    sourceSets["androidMain"].dependencies {
        api("com.mapbox.mapboxsdk:mapbox-android-sdk:9.2.0")
    }

    sourceSets["iosMain"].dependencies {
        implementation("org.jetbrains.kotlin.native.xcode:kotlin-native-xcode-11-4-workaround:1.3.72.0")
    }
}

val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    /// selecting the right configuration for the iOS
    /// framework depending on the environment
    /// variables set by Xcode build
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets
        .getByName<KotlinNativeTarget>("ios")
        .binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)

    /// generate a helpful ./gradlew wrapper with embedded Java path
    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText(
            "#!/bin/bash\n"
                    + "export 'JAVA_HOME=${System.getProperty("java.home")}'\n"
                    + "cd '${rootProject.rootDir}'\n"
                    + "./gradlew \$@\n"
        )
        gradlew.setExecutable(true, false)
    }
}

tasks.getByName("build").dependsOn(packForXcode)