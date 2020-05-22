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
                baseName = "shared"
            }
        }
    }

    android()

    js {
        useCommonJs()
        browser()
    }

    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
        implementation("io.github.dellisd.spatialk:geojson:0.0.1-SNAPSHOT")
        implementation("io.github.dellisd.spatialk:geojson-dsl:0.0.1-SNAPSHOT")
    }

    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        api("com.mapbox.mapboxsdk:mapbox-android-sdk:9.2.0")
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
        gradlew.writeText("#!/bin/bash\n"
                + "export 'JAVA_HOME=${System.getProperty("java.home")}'\n"
                + "cd '${rootProject.rootDir}'\n"
                + "./gradlew \$@\n")
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)